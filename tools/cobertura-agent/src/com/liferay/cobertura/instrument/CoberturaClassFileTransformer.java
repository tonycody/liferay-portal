/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.cobertura.instrument;

import com.liferay.portal.kernel.util.CharPool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import java.lang.instrument.ClassFileTransformer;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.security.ProtectionDomain;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.cobertura.coveragedata.CoverageDataFileHandler;
import net.sourceforge.cobertura.coveragedata.ProjectData;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Shuyang Zhou
 */
public class CoberturaClassFileTransformer implements ClassFileTransformer {

	public CoberturaClassFileTransformer(
		String[] includes, String[] excludes, final File lockFile) {

		_includePatterns = new Pattern[includes.length];

		for (int i = 0; i < includes.length; i++) {
			Pattern pattern = Pattern.compile(includes[i]);

			_includePatterns[i] = pattern;
		}

		_excludePatterns = new Pattern[excludes.length];

		for (int i = 0; i < excludes.length; i++) {
			Pattern pattern = Pattern.compile(excludes[i]);

			_excludePatterns[i] = pattern;
		}

		ProjectDataUtil.addShutdownHook(
			new Runnable() {

				@Override
				public void run() {
					File dataFile =
						CoverageDataFileHandler.getDefaultDataFile();

					Collection<ProjectData> projectDatas =
						_projectDatas.values();

					ProjectDataUtil.mergeSave(
						dataFile, lockFile,
						projectDatas.toArray(
							new ProjectData[projectDatas.size()]));

					_projectDatas.clear();
				}

			}

		);
	}

	public boolean matches(String className) {
		if (_excludePatterns.length != 0) {
			for (Pattern excludePattern : _excludePatterns) {
				Matcher matcher = excludePattern.matcher(className);

				if (matcher.matches()) {
					return false;
				}
			}
		}

		if (_includePatterns.length != 0) {
			for (Pattern includePattern : _includePatterns) {
				Matcher matcher = includePattern.matcher(className);

				if (matcher.matches()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public byte[] transform(
		ClassLoader classLoader, String className, Class<?> refinedClass,
		ProtectionDomain protectionDomain, byte[] classfileBuffer) {

		try {
			if (matches(className)) {
				InstrumentationAgent.recordInstrumentation(
					classLoader, className, classfileBuffer);

				ProjectData projectData = _projectDatas.get(classLoader);

				if (projectData == null) {
					projectData = new ProjectData();

					ProjectData previousProjectData = _projectDatas.putIfAbsent(
						classLoader, projectData);

					if (previousProjectData != null) {
						projectData = previousProjectData;
					}
				}

				ClassWriter classWriter = new ClassWriter(
					ClassWriter.COMPUTE_FRAMES);

				String name = className.replace(
					CharPool.SLASH, CharPool.PERIOD);

				ClassVisitor classVisitor = new CoberturaClassVisitor(
					projectData.getOrCreateClassData(name), classWriter);

				ClassReader classReader = new ClassReader(classfileBuffer);

				synchronized (projectData) {
					classReader.accept(classVisitor, 0);
				}

				byte[] data = classWriter.toByteArray();

				dumpIntrumentedClass(classLoader, className, data);

				return data;
			}

			// Modify TouchCollector's static initialization block by
			// redirecting ProjectData#initialize to
			// InstrumentationAgent#initialize

			if (className.equals(
					"net/sourceforge/cobertura/coveragedata/TouchCollector")) {

				ClassWriter classWriter = new ClassWriter(
					ClassWriter.COMPUTE_FRAMES);

				ClassVisitor classVisitor = new TouchCollectorClassVisitor(
					classWriter);

				ClassReader classReader = new ClassReader(classfileBuffer);

				classReader.accept(classVisitor, 0);

				byte[] data = classWriter.toByteArray();

				dumpIntrumentedClass(classLoader, className, data);

				return data;
			}
		}
		catch (Throwable t) {
			t.printStackTrace();

			throw new RuntimeException(t);
		}

		return null;
	}

	protected void dumpIntrumentedClass(
			ClassLoader classLoader, String className, byte[] data)
		throws IOException {

		if (!Boolean.getBoolean("junit.code.coverage.dump")) {
			return;
		}

		File logFile = new File(_dumpDir, "instrument.log");

		File dumpDir = _dumpDir;

		int index = className.lastIndexOf(CharPool.SLASH);

		if (index != -1) {
			dumpDir = new File(
				dumpDir + "/" + classLoader.toString(),
				className.substring(0, index));

			className = className.substring(index + 1);
		}

		dumpDir.mkdirs();

		File classFile = new File(dumpDir, className + ".class");

		OutputStream outputStream = null;

		try {
			outputStream = new FileOutputStream(classFile);

			outputStream.write(data);
		}
		finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}

		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(logFile, true);

			fileWriter.write(
				"Instrumented " + className + " from " + classLoader +
					" and dumped to " + classFile.getAbsolutePath() + "\n");
		}
		finally {
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}

	private static final File _dumpDir;

	static {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

		String name = runtimeMXBean.getName();

		int index = name.indexOf(CharPool.AT);

		String processId = null;

		if (index == -1) {
			processId = Long.toString(System.currentTimeMillis());
		}
		else {
			processId = name.substring(0, index);
		}

		_dumpDir = new File(
			System.getProperty("java.io.tmpdir"),
			"cobertura-dump/" + processId);
	}

	private Pattern[] _excludePatterns;
	private Pattern[] _includePatterns;
	private ConcurrentMap<ClassLoader, ProjectData> _projectDatas =
		new ConcurrentHashMap<ClassLoader, ProjectData>();

	private static class TouchCollectorClassVisitor extends ClassVisitor {

		public TouchCollectorClassVisitor(ClassVisitor classVisitor) {
			super(Opcodes.ASM5, classVisitor);
		}

		@Override
		public MethodVisitor visitMethod(
			int access, String name, String desc, String signature,
			String[] exceptions) {

			MethodVisitor methodVisitor = cv.visitMethod(
				access, name, desc, signature, exceptions);

			if ((methodVisitor != null) && name.equals("<clinit>")) {
				methodVisitor = new TouchCollectorCLINITVisitor(methodVisitor);
			}

			return methodVisitor;
		}

	}

	private static class TouchCollectorCLINITVisitor extends MethodVisitor {

		public TouchCollectorCLINITVisitor(MethodVisitor methodVisitor) {
			super(Opcodes.ASM5, methodVisitor);
		}

		@Override
		public void visitMethodInsn(
			int opcode, String owner, String name, String desc) {

			visitMethodInsn(opcode, owner, name, desc, false);
		}

		@Override
		public void visitMethodInsn(
			int opcode, String owner, String name, String desc, boolean itf) {

			if ((opcode == Opcodes.INVOKESTATIC) &&
				owner.equals(
					"net/sourceforge/cobertura/coveragedata/ProjectData") &&
				name.equals("initialize") && desc.equals("()V")) {

				owner = "com/liferay/cobertura/instrument/InstrumentationAgent";
			}

			super.visitMethodInsn(opcode, owner, name, desc, itf);
		}

	}

}