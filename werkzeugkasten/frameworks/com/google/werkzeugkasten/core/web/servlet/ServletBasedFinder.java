package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.Finder;

public interface ServletBasedFinder<CTX extends ServletWebContext> extends
		Finder<ServletContext, HttpServletRequest, HttpServletResponse, CTX> {

}
