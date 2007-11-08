package org.examples;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExamplesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4294331309150030622L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("#####################");
		ServletContext ctx = config.getServletContext();

		InputStream in = null;
		try {
			URL u = ctx.getResource("/WEB-INF/faces-config_ut.xml");
			System.out.println(u);
			u = ctx.getResource("/WEB-INF/faces-config.xml");
			in = u.openStream();
			System.out.println(u);
			System.out.println("#####################");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
