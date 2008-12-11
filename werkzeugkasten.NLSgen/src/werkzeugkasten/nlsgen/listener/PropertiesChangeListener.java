package werkzeugkasten.nlsgen.listener;

import static werkzeugkasten.nlsgen.Constants.GENERATOR_TYPE;
import static werkzeugkasten.nlsgen.nls.Strings.GENERATE_CLASSES;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.nlsgen.Activator;
import werkzeugkasten.nlsgen.Constants;
import werkzeugkasten.nlsgen.ResourceGenerator;

public class PropertiesChangeListener implements IResourceChangeListener {

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		int flag = IResourceDelta.ADDED | IResourceDelta.CHANGED;
		if (delta != null && ((delta.getKind() & flag) != 0)) {
			new NLSClassGenJob(delta).schedule();
		}
	}

	public class NLSClassGenJob extends WorkspaceJob {

		protected IResourceDelta delta;

		protected NLSClassGenJob(IResourceDelta delta) {
			super(GENERATE_CLASSES);
			this.delta = delta;
		}

		@Override
		public IStatus runInWorkspace(final IProgressMonitor monitor)
				throws CoreException {
			monitor.beginTask(GENERATE_CLASSES, IProgressMonitor.UNKNOWN);
			this.delta.accept(new IResourceDeltaVisitor() {
				public boolean visit(IResourceDelta delta) throws CoreException {
					if (monitor.isCanceled() == false) {
						IFile r = AdaptableUtil.to(delta.getResource(),
								IFile.class);
						if (r != null
								&& r.exists()
								&& "properties".equals(r.getFullPath()
										.getFileExtension())) {
							ScopedPreferenceStore store = new ScopedPreferenceStore(
									new ProjectScope(r.getProject()),
									Constants.ID_PLUGIN);
							String generator = store
									.getString(GENERATOR_TYPE(r));
							ResourceGenerator rg = Activator
									.createResourceGenerator(generator);
							if (rg != null) {
								rg.generateFrom(r, new SubProgressMonitor(
										monitor, 1));
							}
						}
						monitor.worked(1);
					}
					return true;
				}
			});
			return Status.OK_STATUS;
		}

	}
}
