package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.core.web.Action;
import com.google.werkzeugkasten.core.web.WebContext;
import com.google.werkzeugkasten.meta.Chain;

public abstract class ServletAction implements
		Action<ServletContext, HttpServletRequest, HttpServletResponse> {

	protected static final Chain<Boolean, ServletWebContext<Boolean>> TRUE = new Chain<Boolean, ServletWebContext<Boolean>>() {
		public Boolean execute(ServletWebContext<Boolean> parameter) {
			return Boolean.TRUE;
		}
	};

	protected ServletWebContext<Boolean> matchers;

	protected ServletAction() {
		this.matchers = new ServletWebContext<Boolean>();
	}

	public <CTX extends WebContext<ServletContext, HttpServletRequest, HttpServletResponse, _>> boolean match(
			CTX context) {
		this.matchers.initialize(context.getApplication(),
				context.getRequest(), context.getResponse());
		return this.matchers.execute();
	}
}
