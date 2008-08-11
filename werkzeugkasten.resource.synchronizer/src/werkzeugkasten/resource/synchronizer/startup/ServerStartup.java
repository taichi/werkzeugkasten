package werkzeugkasten.resource.synchronizer.startup;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import werkzeugkasten.resource.synchronizer.job.StartServerJob;

/**
 * 
 * @author taichi
 * 
 */
public class ServerStartup implements IStartup {

	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				new StartServerJob().schedule();
			}
		});
	}
}
