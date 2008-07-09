package werkzeugkasten.nlsgen.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.nlsgen.Activator;
import werkzeugkasten.nlsgen.Constants;
import werkzeugkasten.nlsgen.ResourceGenerator;
import werkzeugkasten.nlsgen.nls.Strings;

/**
 * 
 * @author taichi
 * 
 */
public class NLSAction implements IActionDelegate {

	protected IFile selected;

	@Override
	public void run(IAction action) {
		if (this.selected != null) {
			new WorkspaceJob(Strings.GENERATE_CLASSES) {
				@Override
				public IStatus runInWorkspace(final IProgressMonitor monitor)
						throws CoreException {
					monitor.beginTask(Strings.GENERATE_CLASSES,
							IProgressMonitor.UNKNOWN);
					String key = NLSAction.this.selected
							.getPersistentProperty(Constants.GENERATOR_TYPE);
					ResourceGenerator gen = Activator
							.createResourceGenerator(key);
					try {
						gen.generateFrom(NLSAction.this.selected, monitor);
					} catch (OperationCanceledException e) {
						return Status.CANCEL_STATUS;
					}
					return Status.OK_STATUS;
				}
			}.schedule();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		if (selection.isEmpty()) {
			return;
		}

		IStructuredSelection iss = AdaptableUtil.to(selection,
				IStructuredSelection.class);
		if (iss == null) {
			return;
		}
		IFile file = AdaptableUtil.to(iss.getFirstElement(), IFile.class);
		if (file == null) {
			return;
		}
		IPath full = file.getFullPath();
		if ("properties".equalsIgnoreCase(full.getFileExtension())) {
			this.selected = file;
		}
	}

}
