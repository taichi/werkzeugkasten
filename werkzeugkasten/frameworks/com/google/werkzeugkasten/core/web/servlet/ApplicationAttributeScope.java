package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.meta.ScopeProvider;

public class ApplicationAttributeScope<CTX extends ServletWebContext>
		implements
		ScopeProvider.Writable<ServletContext, HttpServletRequest, HttpServletResponse, CTX> {

	public Object get(CTX context, String key) {
		ServletContext sc = context.getApplication();
		return sc.getAttribute(key);
	}

	public void set(CTX context, String key, Object value) {
		ServletContext sc = context.getApplication();
		sc.setAttribute(key, value);
	}

}
