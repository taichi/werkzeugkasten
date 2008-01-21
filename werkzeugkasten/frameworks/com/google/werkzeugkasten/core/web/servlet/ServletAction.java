package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.Action;

public interface ServletAction<CTX extends ServletWebContext> extends
		Action<ServletContext, HttpServletRequest, HttpServletResponse, CTX> {

}
