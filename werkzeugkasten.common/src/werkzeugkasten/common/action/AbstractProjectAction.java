package werkzeugkasten.common.action;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import werkzeugkasten.common.resource.LogUtil;
import werkzeugkasten.common.resource.ResourceUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;

/**
 * @author taichi
 * 
 */
public abstract class AbstractProjectAction implements IActionDelegate {

	private IProject project = null;

	/**
	 * 
	 */
	public AbstractProjectAction() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object o = ss.getFirstElement();
			this.project = AdaptableUtil.to(o, IProject.class);
			if (this.project == null) {
				IResource r = AdaptableUtil.to(o, IResource.class);
				if (r != null) {
					this.project = r.getProject();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		try {
			if (this.project == null) {
				this.project = ResourceUtil.getCurrentSelectedProject();
			}
			if (this.project != null) {
				run(action, this.project);
			}
		} catch (CoreException e) {
			LogUtil.log(ResourcesPlugin.getPlugin(), e);
		}
	}

	public abstract void run(IAction action, IProject project)
			throws CoreException;
}
