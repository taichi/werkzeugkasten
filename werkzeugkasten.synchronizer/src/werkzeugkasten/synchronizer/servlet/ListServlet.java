package werkzeugkasten.synchronizer.servlet;

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

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.synchronizer.Activator;

public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 7952653999813317193L;

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
			w.println(r.getFullPath());
		}
	}
}
