package werkzeugkasten.synchronizer.action;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import werkzeugkasten.synchronizer.Activator;
import werkzeugkasten.synchronizer.job.StartServerJob;
import werkzeugkasten.synchronizer.job.StopServerJob;
import werkzeugkasten.synchronizer.nls.Images;
import werkzeugkasten.synchronizer.nls.Strings;

public class ToggleServerAction implements IWorkbenchWindowActionDelegate {

	private interface Strategy {

		ImageDescriptor getImage();

		String getText();

		void run() throws CoreException;

	}

	private static class Start implements Strategy {

		public ImageDescriptor getImage() {
			return Images.START;
		}

		public String getText() {
			return Strings.LABEL_START;
		}

		public void run() throws CoreException {
			new StartServerJob().schedule();
		}
	}

	private static class Stop implements Strategy {

		public ImageDescriptor getImage() {
			return Images.STOP;
		}

		public String getText() {
			return Strings.LABEL_STOP;
		}

		public void run() throws CoreException {
			new StopServerJob().schedule();
		}
	}

	private Strategy start;

	private Strategy stop;

	private Strategy current;

	public ToggleServerAction() {
		start = new Start();
		stop = new Stop();
		current = start;
	}

	public void init(IWorkbenchWindow window) {

	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

	public void run(IAction action) {
		try {
			current.run();
			if (current == start) {
				current = stop;
			} else {
				current = start;
			}
			action.setImageDescriptor(current.getImage());
			action.setText(current.getText());
		} catch (CoreException e) {
			Activator.log(e);
		}
	}

	public void dispose() {

	}

}
