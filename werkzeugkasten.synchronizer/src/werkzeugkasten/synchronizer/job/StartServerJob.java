package werkzeugkasten.synchronizer.job;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import werkzeugkasten.synchronizer.Activator;
import werkzeugkasten.synchronizer.nls.Strings;

public class StartServerJob extends WorkspaceJob {

	public StartServerJob() {
		super(Strings.MSG_START_SERVER);
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		monitor.beginTask(Strings.MSG_START_SERVER, 3);
		monitor.worked(1);
		try {
			Activator.startServer();
			monitor.worked(1);
			Activator.refreshToggleAction();
		} finally {
			monitor.worked(1);
			monitor.done();
		}
		return Status.OK_STATUS;
	}

}
