package werkzeugkasten.nlsgen.listener;

import static werkzeugkasten.nlsgen.Constants.GENERATION_TARGET;
import static werkzeugkasten.nlsgen.nls.Strings.GENERATE_CLASSES;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import werkzeugkasten.nlsgen.Activator;
import werkzeugkasten.nlsgen.ResourceGenerator;

public class PropertiesChangeListener implements IResourceChangeListener {

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		new NLSClassGenJob(event.getDelta()).schedule();
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
				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					if (monitor.isCanceled() == false) {
						IResource r = delta.getResource();
						if (r != null
								&& r.exists()
								&& "properties".equals(r.getFullPath()
										.getFileExtension())) {
							String generator = r
									.getPersistentProperty(GENERATION_TARGET);
							ResourceGenerator rg = Activator
									.createResourceGenerator(generator);
							if (rg != null) {
								rg.generateFrom(r, monitor);
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
