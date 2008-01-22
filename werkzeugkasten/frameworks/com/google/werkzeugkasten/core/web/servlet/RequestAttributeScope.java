package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.http.HttpServletRequest;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.ScopeProvider;

public class RequestAttributeScope<CTX extends ServletWebContext> implements
		ScopeProvider.Writable<_, CTX> {

	public Object get(CTX context, String key) {
		HttpServletRequest req = context.getRequest();
		return req.getAttribute(key);
	}

	public void set(CTX context, String key, Object value) {
		HttpServletRequest req = context.getRequest();
		req.setAttribute(key, value);
	}

}
