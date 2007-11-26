package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.WebContext;
import com.google.werkzeugkasten.meta.Disposable;
import com.google.werkzeugkasten.meta.Initializable.Initialize;
import com.google.werkzeugkasten.meta.impl.AbstractChainContext;

public class ServletWebContext<R> extends
		AbstractChainContext<R, ServletWebContext<R>> implements
		WebContext<ServletContext, HttpServletRequest, HttpServletResponse, R>,
		Disposable {

	protected ServletContext application;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	public ServletWebContext() {
	}

	@Initialize
	public void initialize(ServletContext application,
			HttpServletRequest request, HttpServletResponse response) {
		this.application = application;
		this.request = request;
		this.response = response;
	}

	public void dispose() {
		this.application = null;
		this.request = null;
		this.response = null;
	}

	public ServletContext getApplication() {
		return this.application;
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public HttpServletResponse getResponse() {
		return this.response;
	}
}
