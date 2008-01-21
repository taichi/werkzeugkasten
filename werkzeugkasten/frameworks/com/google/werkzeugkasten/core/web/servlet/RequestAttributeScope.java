package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.ScopeProvider;

public class RequestAttributeScope<CTX extends ServletWebContext>
		implements
		ScopeProvider.Writable<ServletContext, HttpServletRequest, HttpServletResponse, CTX> {

	public Object get(CTX context, String key) {
		HttpServletRequest req = context.getRequest();
		return req.getAttribute(key);
	}

	public void set(CTX context, String key, Object value) {
		HttpServletRequest req = context.getRequest();
		req.setAttribute(key, value);
	}

}
