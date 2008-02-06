package com.google.werkzeugkasten.core.web.servlet;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.RequestMatcher;
import com.google.werkzeugkasten.core.web.RequestMethod;

public class ServletRequestMethodMatcher
		implements
		RequestMatcher<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Boolean>> {

	protected Set<String> methods;

	public ServletRequestMethodMatcher(RequestMethod... methods) {
		this.methods = new HashSet<String>(methods.length);
		for (RequestMethod r : methods) {
			this.methods.add(r.name());
		}
	}

	public Boolean execute(ServletWebContext<Boolean> parameter) {
		String method = parameter.getRequest().getMethod();
		if (this.methods.contains(method.toUpperCase())) {
			parameter.execute();
		}
		return false;
	}
}
