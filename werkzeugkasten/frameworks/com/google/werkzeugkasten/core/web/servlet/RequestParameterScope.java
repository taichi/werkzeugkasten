package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.http.HttpServletRequest;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.ScopeProvider;

public class RequestParameterScope implements
		ScopeProvider<Object, _, ServletWebContext<_>> {

	public Object get(ServletWebContext<_> context, String key) {
		HttpServletRequest req = context.getRequest();
		return req.getParameter(key);
	}
}
