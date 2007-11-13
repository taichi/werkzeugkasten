package werkzeugkasten.common.resource;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.ui.WorkbenchUtil;

public class ResourceUtil {

	public static IFile findFile(final String name, IContainer root) {
		final IFile[] file = new IFile[1];
		try {
			IResourceVisitor visitor = new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if (name.equalsIgnoreCase(resource.getName())
							&& resource instanceof IFile) {
						file[0] = (IFile) resource;
						return false;
					}
					return true;
				}
			};
			root.accept(visitor);
		} catch (CoreException e) {
			LogUtil.log(ResourcesPlugin.getPlugin(), e);
		}
		return file[0];
	}

	public static void createDir(IContainer container, String path) {
		try {
			IPath fullpath = new Path(path);
			if (container.exists(fullpath) == false) {
				String[] ary = fullpath.segments();
				StringBuffer stb = new StringBuffer();
				for (int i = 0; i < ary.length; i++) {
					IPath p = new Path(stb.append(ary[i]).toString());
					if (container.exists(p) == false) {
						IFolder f = container.getFolder(p);
						f.create(true, true, null);
					}
					stb.append('/');
				}
			}
		} catch (CoreException e) {
			LogUtil.log(ResourcesPlugin.getPlugin(), e);
		}
	}

	public static <T extends IResource> T getCurrentSelectedResouce(
			Class<T> clazz) {
		T result = null;
		IWorkbenchWindow window = WorkbenchUtil.getWorkbenchWindow();
		if (window != null) {
			result = getCurrentSelectedResouce(window, clazz);
			if (result == null) {
				result = AdaptableUtil.to(getCurrentSelectedResouce(window),
						clazz);
			}
		}
		return result;
	}

	public static IProject getCurrentSelectedProject() {
		IProject result = null;
		IWorkbenchWindow window = WorkbenchUtil.getWorkbenchWindow();
		if (window != null) {
			result = getCurrentSelectedResouce(window, IProject.class);
			if (result == null) {
				Object o = getCurrentSelectedResouce(window);
				result = AdaptableUtil.to(o, IProject.class);
				if (result == null) {
					IResource r = AdaptableUtil.to(o, IResource.class);
					if (r != null) {
						result = r.getProject();
					}
				}
			}
		}
		return result;
	}

	private static <T extends IResource> T getCurrentSelectedResouce(
			IWorkbenchWindow window, Class<T> clazz) {
		IWorkbenchPage page = window.getActivePage();
		if (page != null) {
			// getActiveEditorで取れる参照は、フォーカスがどこにあってもアクティブなエディタの参照が取れてしまう為。
			IWorkbenchPart part = page.getActivePart();
			if (part instanceof IEditorPart) {
				IEditorPart editor = (IEditorPart) part;
				return AdaptableUtil.to(editor.getEditorInput(), clazz);
			}
		}
		return null;
	}

	private static Object getCurrentSelectedResouce(IWorkbenchWindow window) {
		ISelection selection = window.getSelectionService().getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			return ss.getFirstElement();
		}
		return null;
	}

}
