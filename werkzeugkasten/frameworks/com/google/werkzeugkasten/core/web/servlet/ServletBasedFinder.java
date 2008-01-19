package com.google.werkzeugkasten.core.web.servlet;

import java.io.File;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.Finder;
import com.google.werkzeugkasten.core.web.Renderer;

public class ServletBasedFinder
		implements
		Finder<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>> {

	public Renderer<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>> file(
			File file) {
		// TODO Auto-generated method stub
		return null;
	}

	public Renderer<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>> forward(
			String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public Renderer<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>> json() {
		// TODO Auto-generated method stub
		return null;
	}

	public Renderer<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>> redirect(
			String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public Renderer<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>> stream(
			OutputStream out) {
		// TODO Auto-generated method stub
		return null;
	}

	public Renderer<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>> xml() {
		// TODO Auto-generated method stub
		return null;
	}

}
