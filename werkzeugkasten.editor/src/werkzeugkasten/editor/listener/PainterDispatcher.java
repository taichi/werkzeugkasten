/**
 * 
 */
package werkzeugkasten.editor.listener;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

import werkzeugkasten.editor.Activator;

public class PainterDispatcher implements IPartListener2 {
	public void partOpened(IWorkbenchPartReference partRef) {
		Activator.getDefault().addPainter(partRef);
	}

	public void partClosed(IWorkbenchPartReference partRef) {
		Activator.getDefault().removePainter(partRef);
	}

	public void partActivated(IWorkbenchPartReference partRef) {
		Activator.getDefault().addPainter(partRef);
	}

	public void partVisible(IWorkbenchPartReference partRef) {
		Activator.getDefault().addPainter(partRef);
	}

	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		Activator.getDefault().addPainter(partRef);
	}

	public void partDeactivated(IWorkbenchPartReference partRef) {
		Activator.getDefault().addPainter(partRef);
	}

	public void partHidden(IWorkbenchPartReference partRef) {
		Activator.getDefault().addPainter(partRef);
	}

	public void partInputChanged(IWorkbenchPartReference partRef) {
		Activator.getDefault().addPainter(partRef);
	}
}