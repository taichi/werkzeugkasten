package werkzeugkasten.common.ui;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.runtime.LogUtil;

public class WorkbenchUtil {

	public static Shell getShell() {
		IWorkbenchWindow window = getWorkbenchWindow();
		return window != null ? window.getShell() : new Shell(Display
				.getDefault());
	}

	public static IWorkbenchWindow getWorkbenchWindow() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow result = workbench.getActiveWorkbenchWindow();
		if (result == null && 0 < workbench.getWorkbenchWindowCount()) {
			IWorkbenchWindow[] ws = workbench.getWorkbenchWindows();
			result = ws[0];
		}
		return result;
	}

	public static IEditorPart getActiveEditor() {
		IWorkbenchWindow window = getWorkbenchWindow();
		if (window == null) {
			return null;
		}
		final IWorkbenchPage activePage = window.getActivePage();
		if (activePage == null) {
			return null;
		}
		return activePage.getActiveEditor();
	}

	public static IViewPart findView(String viewId) {
		IViewPart vp = null;
		IWorkbenchWindow window = getWorkbenchWindow();
		if (window != null) {
			IWorkbenchPage page = window.getActivePage();
			if (page != null) {
				vp = page.findView(viewId);
			}
		}
		return vp;
	}

	public static IViewPart showView(String viewId) {
		IViewPart vp = null;
		try {
			IWorkbenchWindow window = getWorkbenchWindow();
			if (window != null) {
				IWorkbenchPage page = window.getActivePage();
				if (page != null) {
					vp = page.showView(viewId);
				}
			}
		} catch (PartInitException e) {
			LogUtil.log(ResourcesPlugin.getPlugin(), e);
		}
		return vp;
	}

	public static void selectAndReveal(IResource newResource) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		BasicNewResourceWizard.selectAndReveal(newResource, workbench
				.getActiveWorkbenchWindow());
	}

	public static void setHelp(Composite composite, String contextId) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, contextId);
	}

	public static <T extends IResource> T getCurrentSelectedResouce(
			Class<T> clazz) {
		T result = null;
		IWorkbenchWindow window = getWorkbenchWindow();
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
		IWorkbenchWindow window = getWorkbenchWindow();
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

}
