package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.ScopeProvider;

public class ApplicationAttributeScope implements
		ScopeProvider.Writable<Object, _, ServletWebContext<_>> {

	public Object get(ServletWebContext<_> context, String key) {
		ServletContext sc = context.getApplication();
		return sc.getAttribute(key);
	}

	public void set(ServletWebContext<_> context, String key, Object value) {
		ServletContext sc = context.getApplication();
		sc.setAttribute(key, value);
	}
}
