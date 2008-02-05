package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.ScopeProvider;

public class ApplicationAttributeScope implements
		ScopeProvider.Writable<Object, _, ServletWebContext> {

	public Object get(ServletWebContext context, String key) {
		ServletContext sc = context.getApplication();
		return sc.getAttribute(key);
	}

	public void set(ServletWebContext context, String key, Object value) {
		ServletContext sc = context.getApplication();
		sc.setAttribute(key, value);
	}
}
