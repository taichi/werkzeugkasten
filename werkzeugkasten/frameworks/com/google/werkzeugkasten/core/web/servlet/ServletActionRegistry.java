package com.google.werkzeugkasten.core.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.ActionRegistry;
import com.google.werkzeugkasten.core.web.WebContext;

public class ServletActionRegistry
		implements
		ActionRegistry<ServletContext, HttpServletRequest, HttpServletResponse, ServletAction> {

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

	public <CTX extends WebContext<ServletContext, HttpServletRequest, HttpServletResponse>> ServletAction find(
			CTX context) {
		for (ServletAction a : this.actions) {
			if (a.match(context)) {
				return a;
			}
		}
		return null;
	}

}
