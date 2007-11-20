package werkzeugkasten.editor.startup;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import werkzeugkasten.editor.Activator;

public class EventRegister implements IStartup {

	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow[] windows = workbench.getWorkbenchWindows();
				for (IWorkbenchWindow w : windows) {
					w.getActivePage().addPartListener(new IPartListener2() {
						public void partOpened(IWorkbenchPartReference partRef) {
							Activator.getDefault().addPainter(partRef);
						}

						public void partClosed(IWorkbenchPartReference partRef) {
							Activator.getDefault().removePainter(partRef);
						}

						public void partActivated(
								IWorkbenchPartReference partRef) {
							// do nothing ...
						}

						public void partVisible(IWorkbenchPartReference partRef) {
							// do nothing ...
						}

						public void partBroughtToTop(
								IWorkbenchPartReference partRef) {
							// do nothing ...
						}

						public void partDeactivated(
								IWorkbenchPartReference partRef) {
							// do nothing ...
						}

						public void partHidden(IWorkbenchPartReference partRef) {
							// do nothing ...
						}

						public void partInputChanged(
								IWorkbenchPartReference partRef) {
							// do nothing ...
						}

					});
				}
			}
		});
	}
}
