package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.UriMatcher;

public interface ServletBasedUriMatcher extends
		UriMatcher<ServletContext, HttpServletRequest, HttpServletResponse> {

}
