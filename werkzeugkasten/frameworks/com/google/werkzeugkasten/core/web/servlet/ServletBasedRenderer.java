package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.Renderer;

public interface ServletBasedRenderer<CTX extends ServletWebContext> extends
		Renderer<ServletContext, HttpServletRequest, HttpServletResponse, CTX> {

}
