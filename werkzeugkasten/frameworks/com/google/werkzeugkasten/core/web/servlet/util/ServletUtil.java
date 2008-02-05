package com.google.werkzeugkasten.core.web.servlet.util;

import javax.servlet.http.HttpServletRequest;

import com.google.werkzeugkasten.util.StringUtil;

public class ServletUtil {

	public static String getRequestPath(HttpServletRequest request) {
		String result = null;
		String contextPath = request.getContextPath();
		if ("/".equals(contextPath) || StringUtil.isEmpty(contextPath)) {
			result = request.getRequestURI();
		} else {
			result = request.getRequestURI().substring(contextPath.length());
		}
		return result;
	}
}
