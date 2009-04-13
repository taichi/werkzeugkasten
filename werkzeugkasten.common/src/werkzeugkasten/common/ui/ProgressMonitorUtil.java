package werkzeugkasten.common.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;

/**
 * @author taichi
 * 
 */
public class ProgressMonitorUtil {

	public static void isCanceled(IProgressMonitor monitor) {
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
	}

	public static void isCanceled(IProgressMonitor monitor, int work) {
		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		monitor.worked(work);
	}

	public static IProgressMonitor care(IProgressMonitor monitor) {
		if (monitor == null) {
			return new NullProgressMonitor();
		}
		return monitor;
	}
}
