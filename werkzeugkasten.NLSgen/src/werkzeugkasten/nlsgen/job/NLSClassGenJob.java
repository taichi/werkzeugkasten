package werkzeugkasten.nlsgen.job;

import static werkzeugkasten.nlsgen.Constants.GENERATOR_TYPE;
import static werkzeugkasten.nlsgen.nls.Strings.GENERATE_CLASSES;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ILock;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.ui.ProgressMonitorUtil;
import werkzeugkasten.nlsgen.Activator;
import werkzeugkasten.nlsgen.Constants;
import werkzeugkasten.nlsgen.ResourceGenerator;

public class NLSClassGenJob extends WorkspaceJob {

	protected static final Map<IFile, ILock> scheduler = new ConcurrentHashMap<IFile, ILock>();

	protected IResourceDelta delta;

	public NLSClassGenJob(IResourceDelta delta) {
		super(GENERATE_CLASSES);
		this.delta = delta;
	}

	@Override
	public IStatus runInWorkspace(final IProgressMonitor monitor)
			throws CoreException {
		monitor.beginTask(GENERATE_CLASSES, IProgressMonitor.UNKNOWN);
		this.delta.accept(new IResourceDeltaVisitor() {
			public boolean visit(IResourceDelta delta) throws CoreException {
				ProgressMonitorUtil.isCanceled(monitor, 1);

				IFile r = AdaptableUtil.to(delta.getResource(), IFile.class);
				if (r != null
						&& r.exists()
						&& "properties".equals(r.getFullPath()
								.getFileExtension())) {
					ScopedPreferenceStore store = new ScopedPreferenceStore(
							new ProjectScope(r.getProject()),
							Constants.ID_PLUGIN);
					String generator = store.getString(GENERATOR_TYPE(r));
					ResourceGenerator rg = Activator
							.createResourceGenerator(generator);
					if (rg != null) {
						try {
							acquireLock(r);
							rg.generateFrom(r, new SubProgressMonitor(monitor,
									1));
						} finally {
							releaseLock(r);
						}
					}
				}
				return true;
			}
		});
		return Status.OK_STATUS;
	}

	protected void acquireLock(IFile target) {
		ILock lock = scheduler.get(target);
		if (lock == null) {
			synchronized (scheduler) {
				lock = Job.getJobManager().newLock();
				scheduler.put(target, lock);
			}
		}
		lock.acquire();
	}

	protected void releaseLock(IFile target) {
		ILock lock = scheduler.remove(target);
		if (lock != null) {
			lock.release();
		}
	}

}