package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.http.HttpServletRequest;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.ScopeProvider;

public class RequestAttributeScope implements
		ScopeProvider.Writable<Object, _, ServletWebContext<_>> {

	public Object get(ServletWebContext<_> context, String key) {
		HttpServletRequest req = context.getRequest();
		return req.getAttribute(key);
	}

	public void set(ServletWebContext<_> context, String key, Object value) {
		HttpServletRequest req = context.getRequest();
		req.setAttribute(key, value);
	}
}
