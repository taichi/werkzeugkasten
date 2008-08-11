/*
 * Copyright 2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package werkzeugkasten.resource.synchronizer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.seasar.eclipse.common.runtime.AdaptableUtil;

import werkzeugkasten.resource.synchronizer.Activator;

/**
 * 
 * @author taichi
 * 
 */
public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 7952653999813317193L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter w = response.getWriter();
		try {
			int i = 0;
			for (Enumeration e = request.getParameterNames(); e
					.hasMoreElements(); i++) {
				String s = e.nextElement().toString();
				IResource r = root.findMember(s);
				if (r != null && r.exists()) {
					IContainer c = AdaptableUtil.to(r, IContainer.class);
					if (c != null) {
						println(w, c);
					} else {
						w.println(r.getFullPath());
					}
				}
			}
			if (i < 1) {
				println(w, root);
			}
		} catch (CoreException e) {
			Activator.log(e);
		} finally {
			if (w != null) {
				w.close();
			}
		}
	}

	protected void println(PrintWriter w, IContainer c) throws CoreException {
		for (IResource r : c.members()) {
			w.print(r.getFullPath());
			if (r instanceof IContainer) {
				w.println("/");
			}
		}
	}
}
