package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.ScopeProvider;

public class RequestParameterScope<CTX extends ServletWebContext>
		implements
		ScopeProvider<ServletContext, HttpServletRequest, HttpServletResponse, CTX> {

	public Object get(CTX context, String key) {
		HttpServletRequest req = context.getRequest();
		return req.getParameter(key);
	}

}
