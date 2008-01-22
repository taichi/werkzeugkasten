package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.http.HttpServletRequest;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.ScopeProvider;

public class RequestParameterScope<CTX extends ServletWebContext> implements
		ScopeProvider<_, CTX> {

	public Object get(CTX context, String key) {
		HttpServletRequest req = context.getRequest();
		return req.getParameter(key);
	}

}
