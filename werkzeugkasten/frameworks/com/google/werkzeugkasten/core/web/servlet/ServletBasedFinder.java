package com.google.werkzeugkasten.core.web.servlet;

import java.io.File;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.Finder;

public interface ServletBasedFinder extends
		Finder<ServletContext, HttpServletRequest, HttpServletResponse> {

	ServletBasedRenderer json(Object obj);

	ServletBasedRenderer xml();

	ServletBasedRenderer file(File file);

	ServletBasedRenderer forward(String path);

	ServletBasedRenderer redirect(String path);

	ServletBasedRenderer stream(OutputStream out);

}
