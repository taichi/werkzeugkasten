package com.google.werkzeugkasten.core.web.servlet;

import java.io.File;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.Finder;

public interface ServletBasedFinder<CTX extends ServletWebContext> extends
		Finder<ServletContext, HttpServletRequest, HttpServletResponse, CTX> {

	ServletBasedRenderer<CTX> json(Object obj);

	ServletBasedRenderer<CTX> xml();

	ServletBasedRenderer<CTX> file(File file);

	ServletBasedRenderer<CTX> forward(String path);

	ServletBasedRenderer<CTX> redirect(String path);

	ServletBasedRenderer<CTX> stream(OutputStream out);

}
