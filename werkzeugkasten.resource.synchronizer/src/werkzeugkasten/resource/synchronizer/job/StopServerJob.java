package werkzeugkasten.resource.synchronizer.job;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import werkzeugkasten.resource.synchronizer.Activator;
import werkzeugkasten.resource.synchronizer.nls.Strings;

/**
 * 
 * @author taichi
 * 
 */
public class StopServerJob extends WorkspaceJob {

	public StopServerJob() {
		super(Strings.MSG_STOP_SERVER);
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		monitor.beginTask(Strings.MSG_STOP_SERVER, 3);
		monitor.worked(1);
		try {
			Activator.stopServer();
			monitor.worked(1);
			Activator.refreshToggleAction();
		} finally {
			monitor.worked(1);
			monitor.done();
		}
		return Status.OK_STATUS;
	}
}
