package werkzeugkasten.common.ui;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import werkzeugkasten.common.resource.LogUtil;

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
}
