package werkzeugkasten.synchronizer.job;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import werkzeugkasten.synchronizer.Activator;
import werkzeugkasten.synchronizer.nls.Strings;

public class StopServerJob extends WorkspaceJob {

	public StopServerJob() {
		super(Strings.MSG_STOP_SERVER);
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		monitor.beginTask(Strings.MSG_STOP_SERVER, 2);
		monitor.worked(1);
		try {
			Activator.stopServer();
		} finally {
			monitor.worked(1);
			monitor.done();
		}
		return Status.OK_STATUS;
	}

}
