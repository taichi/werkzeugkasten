package werkzeugkasten.common.resource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.runtime.LogUtil;
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
			IResource r = getCurrentSelectedResouce(window, IResource.class);
			if (r == null) {
				Object o = getCurrentSelectedResouce(window);
				result = AdaptableUtil.to(o, IProject.class);
				if (result == null) {
					r = AdaptableUtil.to(o, IResource.class);
				}
			}
			if (r != null) {
				result = r.getProject();
			}
		}
		return result;
	}

	public interface EditorPartSeeker {
		<T extends IResource> T seek(IEditorPart part, Class<T> clazz);
	}

	public static Set<EditorPartSeeker> seekers = Collections
			.synchronizedSet(new HashSet<EditorPartSeeker>());

	static {
		seekers.add(new EditorPartSeeker() {
			public <T extends IResource> T seek(IEditorPart part, Class<T> clazz) {
				IEditorInput input = part.getEditorInput();
				return AdaptableUtil.to(input, clazz);
			}
		});
		seekers.add(new EditorPartSeeker() {
			public <T extends IResource> T seek(IEditorPart part, Class<T> clazz) {
				IEditorInput input = part.getEditorInput();
				IFileEditorInput file = AdaptableUtil.to(input,
						IFileEditorInput.class);
				if (file != null) {
					return AdaptableUtil.to(file.getFile(), clazz);
				}
				return null;
			}
		});
	}

	private static <T extends IResource> T getCurrentSelectedResouce(
			IWorkbenchWindow window, Class<T> clazz) {
		IWorkbenchPage page = window.getActivePage();
		if (page != null) {
			// getActiveEditorで取れる参照は、フォーカスがどこにあってもアクティブなエディタの参照が取れてしまう為。
			IWorkbenchPart wpart = page.getActivePart();
			IEditorPart part = AdaptableUtil.to(wpart, IEditorPart.class);
			if (part != null) {
				for (EditorPartSeeker seeker : seekers) {
					T result = seeker.seek(part, clazz);
					if (result != null) {
						return result;
					}
				}
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

	public static void copyFile(IContainer container, URL u)
			throws CoreException, IOException {
		IPath p = new Path(u.getFile());
		String s = p.lastSegment();
		IFile newone = container.getFile(new Path(s));
		if (newone != null && newone.exists() == false) {
			newone.create(new BufferedInputStream(u.openStream()), true, null);
		}
	}

	public static Map<IProject, List<IPath>> toProjectPathMap(Iterator<?> i) {
		Map<IProject, List<IPath>> map = new LinkedHashMap<IProject, List<IPath>>();
		while (i.hasNext()) {
			IContainer c = AdaptableUtil.to(i.next(), IContainer.class);
			if (c != null) {
				IProject p = c.getProject();
				List<IPath> list = map.get(p);
				if (list == null) {
					list = new ArrayList<IPath>();
					map.put(p, list);
				}
				list.add(c.getFullPath());
			}
		}
		return map;
	}

}
