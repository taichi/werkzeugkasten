package com.google.werkzeugkasten.core.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.werkzeugkasten.core.web.RequestCoordinator;

public interface ServletRequestCoordinator<CTL>
		extends
		RequestCoordinator<ServletContext, HttpServletRequest, HttpServletResponse, ServletWebContext<Void>, CTL, ServletAction> {

}
