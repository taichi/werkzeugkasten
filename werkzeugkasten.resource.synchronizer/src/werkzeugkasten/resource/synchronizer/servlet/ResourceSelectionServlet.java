package werkzeugkasten.resource.synchronizer.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.ui.texteditor.ITextEditor;

import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.ui.WorkbenchUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.resource.synchronizer.Activator;

public class ResourceSelectionServlet extends HttpServlet {

	private static final long serialVersionUID = 2309638457502547991L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String path = req.getParameter("path");
		final int offset = SelectionServlet.toLine(req.getParameter("offset"),
				0);

		if (StringUtil.isEmpty(path) == false) {
			IWorkspaceRoot root = ProjectUtil.getWorkspaceRoot();
			final IResource r = root.findMember(path);
			if (r != null && r.exists() && r.isAccessible()
					&& r.getType() == IResource.FILE) {
				resp.setStatus(HttpServletResponse.SC_OK);
				new WorkbenchJob("") {
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						try {
							monitor.beginTask("open resource", 0);
							IWorkbenchWindow window = WorkbenchUtil
									.getWorkbenchWindow();
							if (window != null) {
								IWorkbenchPage page = window.getActivePage();
								if (page != null) {
									IEditorPart editor = IDE.openEditor(page,
											(IFile) r, true);
									ITextEditor te = AdaptableUtil.to(editor,
											ITextEditor.class);
									if (te != null) {
										te.selectAndReveal(offset, 0);
									}
								}
							}
						} catch (Exception e) {
							Activator.log(e);
						} finally {
							monitor.done();
						}
						return Status.OK_STATUS;
					}
				}.schedule();
			} else {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		resp.getWriter().println(path);
	}
}
