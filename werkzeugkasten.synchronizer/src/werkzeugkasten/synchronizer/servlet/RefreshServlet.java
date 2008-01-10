package werkzeugkasten.synchronizer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

import werkzeugkasten.synchronizer.nls.Strings;

public class RefreshServlet extends HttpServlet {

	private static final long serialVersionUID = -7685837059141908646L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		response.setStatus(HttpServletResponse.SC_OK);
		PrintWriter w = null;
		try {
			w = response.getWriter();
			final List<RefreshTask> list = new LinkedList<RefreshTask>();
			int i = 0;
			for (Enumeration e = request.getParameterNames(); e
					.hasMoreElements(); i++) {
				String s = e.nextElement().toString();
				IResource r = root.findMember(s);
				String depth = request.getParameter(s);
				if (r != null && r.exists()) {
					list.add(new RefreshTask(r, toDepth(depth)));
					w.println(r.getFullPath());
				}
			}
			if (i < 1) {
				list.add(new RefreshTask(root, IResource.DEPTH_INFINITE));
			}
			new WorkspaceJob(Strings.MSG_REFRESH_RESOURCE) {
				@Override
				public IStatus runInWorkspace(IProgressMonitor monitor)
						throws CoreException {
					monitor
							.beginTask(Strings.MSG_REFRESH_RESOURCE, list
									.size());
					for (RefreshTask rt : list) {
						rt.run(new SubProgressMonitor(monitor, 1));
					}
					monitor.done();
					return Status.OK_STATUS;
				}
			}.schedule();
		} finally {
			if (w != null) {
				w.close();
			}
		}
	}

	private class RefreshTask {
		protected IResource r;
		protected int depth;

		public RefreshTask(IResource r, int depth) {
			this.r = r;
			this.depth = depth;
		}

		public void run(IProgressMonitor monitor) throws CoreException {
			r.refreshLocal(depth, monitor);
		}
	}

	protected static final Map<String, Integer> STRING_TO_DEPTH = new HashMap<String, Integer>();
	static {
		STRING_TO_DEPTH.put("ZERO", IResource.DEPTH_ZERO);
		STRING_TO_DEPTH.put("ONE", IResource.DEPTH_ONE);
		STRING_TO_DEPTH.put("INFINITE", IResource.DEPTH_INFINITE);
	}

	protected int toDepth(String depth) {
		if (depth == null || depth.length() < 1) {
			return IResource.DEPTH_ONE;
		}
		Integer i = STRING_TO_DEPTH.get(depth.toUpperCase());
		return i != null ? i : IResource.DEPTH_ONE;
	}
}
