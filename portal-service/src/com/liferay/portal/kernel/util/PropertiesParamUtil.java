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

package com.liferay.portal.kernel.util;

import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class PropertiesParamUtil {

	public static boolean getBoolean(
		Properties properties, HttpServletRequest request, String param) {

		return getBoolean(
			properties, request, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		Properties properties, HttpServletRequest request, String param,
		boolean defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		boolean getterUtilValue = GetterUtil.getBoolean(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static boolean getBoolean(
		Properties properties, PortletRequest portletRequest, String param) {

		return getBoolean(
			properties, portletRequest, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		Properties properties, PortletRequest portletRequest, String param,
		boolean defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		boolean getterUtilValue = GetterUtil.getBoolean(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static boolean getBoolean(
		UnicodeProperties properties, HttpServletRequest request,
		String param) {

		return getBoolean(
			properties, request, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		UnicodeProperties properties, HttpServletRequest request, String param,
		boolean defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		boolean getterUtilValue = GetterUtil.getBoolean(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static boolean getBoolean(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param) {

		return getBoolean(
			properties, portletRequest, param, GetterUtil.DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param, boolean defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		boolean getterUtilValue = GetterUtil.getBoolean(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static double getDouble(
		Properties properties, HttpServletRequest request, String param) {

		return getDouble(properties, request, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		Properties properties, HttpServletRequest request, String param,
		double defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		double getterUtilValue = GetterUtil.getDouble(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static double getDouble(
		Properties properties, PortletRequest portletRequest, String param) {

		return getDouble(
			properties, portletRequest, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		Properties properties, PortletRequest portletRequest, String param,
		double defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		double getterUtilValue = GetterUtil.getDouble(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static double getDouble(
		UnicodeProperties properties, HttpServletRequest request,
		String param) {

		return getDouble(properties, request, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		UnicodeProperties properties, HttpServletRequest request, String param,
		double defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		double getterUtilValue = GetterUtil.getDouble(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static double getDouble(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param) {

		return getDouble(
			properties, portletRequest, param, GetterUtil.DEFAULT_DOUBLE);
	}

	public static double getDouble(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param, double defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		double getterUtilValue = GetterUtil.getDouble(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static int getInteger(
		Properties properties, HttpServletRequest request, String param) {

		return getInteger(
			properties, request, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		Properties properties, HttpServletRequest request, String param,
		int defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		int getterUtilValue = GetterUtil.getInteger(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static int getInteger(
		Properties properties, PortletRequest portletRequest, String param) {

		return getInteger(
			properties, portletRequest, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		Properties properties, PortletRequest portletRequest, String param,
		int defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		int getterUtilValue = GetterUtil.getInteger(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static int getInteger(
		UnicodeProperties properties, HttpServletRequest request,
		String param) {

		return getInteger(
			properties, request, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		UnicodeProperties properties, HttpServletRequest request, String param,
		int defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		int getterUtilValue = GetterUtil.getInteger(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static int getInteger(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param) {

		return getInteger(
			properties, portletRequest, param, GetterUtil.DEFAULT_INTEGER);
	}

	public static int getInteger(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param, int defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		int getterUtilValue = GetterUtil.getInteger(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static long getLong(
		Properties properties, HttpServletRequest request, String param) {

		return getLong(properties, request, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		Properties properties, HttpServletRequest request, String param,
		long defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		long getterUtilValue = GetterUtil.getLong(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static long getLong(
		Properties properties, PortletRequest portletRequest, String param) {

		return getLong(
			properties, portletRequest, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		Properties properties, PortletRequest portletRequest, String param,
		long defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		long getterUtilValue = GetterUtil.getLong(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static long getLong(
		UnicodeProperties properties, HttpServletRequest request,
		String param) {

		return getLong(properties, request, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		UnicodeProperties properties, HttpServletRequest request, String param,
		long defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		long getterUtilValue = GetterUtil.getLong(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static long getLong(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param) {

		return getLong(
			properties, portletRequest, param, GetterUtil.DEFAULT_LONG);
	}

	public static long getLong(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param, long defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		long getterUtilValue = GetterUtil.getLong(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static UnicodeProperties getProperties(
		HttpServletRequest request, String prefix) {

		UnicodeProperties properties = new UnicodeProperties(true);

		Map<String, String[]> parameterMap = request.getParameterMap();

		List<String> params = filterParams(
			prefix, ListUtil.fromCollection(parameterMap.keySet()), null);

		for (String param : params) {
			String key = param.substring(prefix.length(), param.length() - 2);

			String value = request.getParameter(param);

			properties.setProperty(key, value);
		}

		String checkboxNames = ParamUtil.getString(request, "checkboxNames");

		addCheckboxValues(properties, params, prefix, checkboxNames);

		return properties;
	}

	public static UnicodeProperties getProperties(
		PortletRequest portletRequest, String prefix) {

		UnicodeProperties properties = new UnicodeProperties(true);

		Map<String, String[]> parameterMap = portletRequest.getParameterMap();

		List<String> params = filterParams(
			prefix, ListUtil.fromCollection(parameterMap.keySet()), null);

		for (String param : params) {
			String[] values = portletRequest.getParameterValues(param);

			String value = StringUtil.merge(values);

			properties.setProperty(getKey(param, prefix), value);
		}

		String checkboxNames = ParamUtil.getString(
			portletRequest, "checkboxNames");

		addCheckboxValues(properties, params, prefix, checkboxNames);

		return properties;
	}

	public static UnicodeProperties getProperties(
		ServiceContext serviceContext, String prefix) {

		UnicodeProperties properties = new UnicodeProperties(true);

		Map<String, Serializable> attributes = serviceContext.getAttributes();

		List<String> params = filterParams(
			prefix, ListUtil.fromCollection(attributes.keySet()), null);

		for (String param : params) {
			String value = ParamUtil.getString(serviceContext, param);

			properties.setProperty(getKey(param, prefix), value);
		}

		String checkboxNames = ParamUtil.getString(
			serviceContext, "checkboxNames");

		addCheckboxValues(properties, params, prefix, checkboxNames);

		return properties;
	}

	public static String getString(
		Properties properties, HttpServletRequest request, String param) {

		return getString(properties, request, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		Properties properties, HttpServletRequest request, String param,
		String defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		String getterUtilValue = GetterUtil.getString(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static String getString(
		Properties properties, PortletRequest portletRequest, String param) {

		return getString(
			properties, portletRequest, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		Properties properties, PortletRequest portletRequest, String param,
		String defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		String getterUtilValue = GetterUtil.getString(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	public static String getString(
		UnicodeProperties properties, HttpServletRequest request,
		String param) {

		return getString(properties, request, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		UnicodeProperties properties, HttpServletRequest request, String param,
		String defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		String getterUtilValue = GetterUtil.getString(
			propertiesValue, defaultValue);

		return ParamUtil.get(request, param, getterUtilValue);
	}

	public static String getString(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param) {

		return getString(
			properties, portletRequest, param, GetterUtil.DEFAULT_STRING);
	}

	public static String getString(
		UnicodeProperties properties, PortletRequest portletRequest,
		String param, String defaultValue) {

		String propertiesValue = properties.getProperty(param, null);

		String getterUtilValue = GetterUtil.getString(
			propertiesValue, defaultValue);

		return ParamUtil.get(portletRequest, param, getterUtilValue);
	}

	protected static void addCheckboxValues(
		UnicodeProperties properties, List<String> params, String prefix,
		String checkboxNames) {

		if (Validator.isNull(checkboxNames)) {
			return;
		}

		List<String> checkboxParams = filterParams(
			prefix, ListUtil.fromString(checkboxNames, StringPool.COMMA),
			params);

		for (String param : checkboxParams) {
			properties.setProperty(
				getKey(param, prefix), Boolean.FALSE.toString());
		}
	}

	protected static List<String> filterParams(
		final String prefix, List<String> params,
		final List<String> excludedParams) {

		PredicateFilter<String> predicateFilter =
			new PredicateFilter<String>() {

				@Override
				public boolean filter(String param) {
					if (!param.startsWith(prefix)) {
						return false;
					}

					if ((excludedParams != null) &&
						excludedParams.contains(param)) {

						return false;
					}

					return true;
				}

			};

		return ListUtil.filter(params, predicateFilter);
	}

	protected static String getKey(String param, String prefix) {
		return param.substring(prefix.length(), param.length() - 2);
	}

}