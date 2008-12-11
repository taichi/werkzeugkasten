package werkzeugkasten.nlsgen.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ProjectScope;
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
import org.eclipse.ui.preferences.ScopedPreferenceStore;

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

	protected List<IFile> selectedFiles;

	public void run(IAction action) {
		if (this.selectedFiles != null && 0 < this.selectedFiles.size()) {
			final List<IFile> files = new ArrayList<IFile>(this.selectedFiles);
			new WorkspaceJob(Strings.GENERATE_CLASSES) {
				@Override
				public IStatus runInWorkspace(final IProgressMonitor monitor)
						throws CoreException {
					for (IFile selected : files) {
						ScopedPreferenceStore store = new ScopedPreferenceStore(
								new ProjectScope(selected.getProject()),
								Constants.ID_PLUGIN);
						String key = store.getString(Constants
								.GENERATOR_TYPE(selected));
						ResourceGenerator gen = Activator
								.createResourceGenerator(key);
						try {
							if (gen != null) {
								gen.generateFrom(selected, monitor);
							}
						} catch (OperationCanceledException e) {
							return Status.CANCEL_STATUS;
						}
					}
					return Status.OK_STATUS;
				}
			}.schedule();
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		if (selection.isEmpty()) {
			return;
		}

		IStructuredSelection iss = AdaptableUtil.to(selection,
				IStructuredSelection.class);
		if (iss == null) {
			return;
		}
		List<IFile> files = new ArrayList<IFile>();
		for (Iterator<?> i = iss.iterator(); i.hasNext();) {
			IFile file = AdaptableUtil.to(i.next(), IFile.class);
			if (file != null) {
				IPath full = file.getFullPath();
				if ("properties".equalsIgnoreCase(full.getFileExtension())) {
					ScopedPreferenceStore store = new ScopedPreferenceStore(
							new ProjectScope(file.getProject()),
							Constants.ID_PLUGIN);
					String key = store
							.getString(Constants.GENERATOR_TYPE(file));
					ResourceGenerator gen = Activator
							.createResourceGenerator(key);
					if (gen != null) {
						files.add(file);
					}
				}
			}
		}
		this.selectedFiles = files;
		action.setEnabled(0 < this.selectedFiles.size());
	}

}
