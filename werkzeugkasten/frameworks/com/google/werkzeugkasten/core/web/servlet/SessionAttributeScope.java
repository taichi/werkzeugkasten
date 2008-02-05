package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.ScopeProvider;

public class SessionAttributeScope implements
		ScopeProvider.Writable<Object, _, ServletWebContext<_>> {

	public Object get(ServletWebContext<_> context, String key) {
		HttpServletRequest req = context.getRequest();
		HttpSession session = req.getSession(false);
		return session != null ? session.getAttribute(key) : null;
	}

	public void set(ServletWebContext<_> context, String key, Object value) {
		HttpServletRequest req = context.getRequest();
		HttpSession session = req.getSession();
		session.setAttribute(key, value);
	}
}
