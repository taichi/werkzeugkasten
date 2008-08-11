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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;

import werkzeugkasten.resource.synchronizer.Activator;

/**
 * 
 * @author taichi
 * 
 */
public class SrcLocationServlet extends HttpServlet {

	private static final long serialVersionUID = 3494067233019408993L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
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
			for (Enumeration e = request.getParameterNames(); e
					.hasMoreElements();) {
				String s = e.nextElement().toString();
				IProject project = root.getProject(s);
				if (project != null && project.exists()) {
					IJavaProject javap = JavaCore.create(project);
					for (IPackageFragmentRoot pfr : javap
							.getPackageFragmentRoots()) {
						if (pfr.getKind() == IPackageFragmentRoot.K_SOURCE) {
							String p = pfr.getResource().getLocation()
									.toString();
							w.println(p);
						}
					}
				}
			}
		} catch (CoreException e) {
			Activator.log(e);
		} finally {
			if (w != null) {
				w.close();
			}
		}
	}
}
