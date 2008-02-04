package com.google.werkzeugkasten.core.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.ActionRegistry;

public class ServletActionRegistry<CTX extends ServletWebContext>
		implements
		ActionRegistry<ServletContext, HttpServletRequest, HttpServletResponse, CTX, ServletAction<CTX>> {

	protected List<ServletAction<CTX>> actions = new ArrayList<ServletAction<CTX>>();

	public void add(ServletAction<CTX>... actions) {
		for (ServletAction<CTX> a : actions) {
			this.actions.add(a);
		}
	}

	public void remove(ServletAction<CTX>... actions) {
		for (ServletAction<CTX> a : actions) {
			this.actions.remove(a);
		}
	}

	public ServletAction<CTX> find(CTX context) {
		for (ServletAction<CTX> a : this.actions) {
			if (a.match(context)) {
				return a;
			}
		}
		return null;
	}

}
