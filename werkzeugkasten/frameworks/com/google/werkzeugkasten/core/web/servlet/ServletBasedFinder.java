package com.google.werkzeugkasten.core.web.servlet;

import java.io.File;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.Finder;

public class ServletBasedFinder
		implements
		Finder<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>> {

	public ServletBasedRenderer file(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletBasedRenderer forward(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletBasedRenderer json(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletBasedRenderer redirect(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletBasedRenderer stream(OutputStream out) {
		// TODO Auto-generated method stub
		return null;
	}

	public ServletBasedRenderer xml() {
		// TODO Auto-generated method stub
		return null;
	}

}
