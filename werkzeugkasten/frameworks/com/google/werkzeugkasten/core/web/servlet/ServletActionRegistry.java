package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.impl.DefaultActionRegistry;

public class ServletActionRegistry
		extends
		DefaultActionRegistry<ServletContext, HttpServletRequest, HttpServletResponse, ServletAction> {

}
