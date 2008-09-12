package werkzeugkasten.weblauncher.action;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;

import werkzeugkasten.common.action.EnablerAction;
import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.job.StartServerJob;
import werkzeugkasten.weblauncher.nls.Images;
import werkzeugkasten.weblauncher.nls.Strings;

/**
 * @author taichi
 * 
 */
public class ToggleServerAction extends EnablerAction {

	private interface Strategy {

		ImageDescriptor getImage();

		String getText();

		void run(IAction action, IProject project) throws CoreException;

	}

	private static class Start implements Strategy {
		public ImageDescriptor getImage() {
			return Images.START;
		}

		public String getText() {
			return Strings.LABEL_START;
		}

		public void run(IAction action, IProject project) throws CoreException {
			if (checkEnabled(project)) {
				Job job = new StartServerJob(project);
				job.schedule();
			}
		}
	}

	private static class Stop implements Strategy {
		public ImageDescriptor getImage() {
			return Images.STOP;
		}

		public String getText() {
			return Strings.LABEL_STOP;
		}

		public void run(IAction action, IProject project) throws CoreException {
			ILaunch launch = Activator.getLaunch(project);
			if (launch != null) {
				launch.terminate();
			}
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

	protected synchronized boolean checkEnabled() {
		IProject project = Activator.findCurrentProject();
		boolean result = checkEnabled(project);
		if (result) {
			ILaunch launch = Activator.getLaunch(project);
			if (launch == null || launch.isTerminated()) {
				current = start;
			} else {
				current = stop;
			}
			delegate.setImageDescriptor(current.getImage());
			delegate.setText(current.getText());
		}
		return result;
	}

	private static boolean checkEnabled(IProject project) {
		return project != null
				&& ProjectUtil.hasNature(project, Constants.ID_NATURE);
	}

	public void run(IAction action) {
		try {
			IProject project = Activator.findCurrentProject();
			if (project != null) {
				current.run(action, project);
				if (current == start) {
					current = stop;
				} else {
					current = start;
				}
				action.setImageDescriptor(current.getImage());
				action.setText(current.getText());
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
	}
}
