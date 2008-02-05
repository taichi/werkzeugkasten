package com.google.werkzeugkasten.core.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.ActionRegistry;

public class ServletActionRegistry<CTX extends ServletWebContext>
		implements
		ActionRegistry<ServletContext, HttpServletRequest, HttpServletResponse, CTX, ServletAction> {

	protected List<ServletAction> actions = new ArrayList<ServletAction>();

	public void add(ServletAction... actions) {
		for (ServletAction a : actions) {
			this.actions.add(a);
		}
	}

	public void remove(ServletAction... actions) {
		for (ServletAction a : actions) {
			this.actions.remove(a);
		}
	}

	public ServletAction find(CTX context) {
		for (ServletAction a : this.actions) {
			if (a.match(context)) {
				return a;
			}
		}
		return null;
	}

}
