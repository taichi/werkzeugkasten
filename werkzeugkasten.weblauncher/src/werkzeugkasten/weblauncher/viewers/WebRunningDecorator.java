package werkzeugkasten.weblauncher.viewers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.ITerminate;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.osgi.util.NLS;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.viewers.AbstractLightweightLabelDecorator;
import werkzeugkasten.weblauncher.Activator;
import werkzeugkasten.weblauncher.Constants;
import werkzeugkasten.weblauncher.nls.Images;
import werkzeugkasten.weblauncher.nls.Strings;
import werkzeugkasten.weblauncher.preferences.WebPreferences;

public class WebRunningDecorator extends AbstractLightweightLabelDecorator {

	public void decorate(Object element, IDecoration decoration) {
		try {
			IProject project = AdaptableUtil.to(element, IProject.class);
			if (project != null) {
				Object o = project
						.getSessionProperty(Constants.KEY_SERVER_STATE);
				if (o instanceof ITerminate) {
					ITerminate t = (ITerminate) o;
					if (t.isTerminated()) {
						project.setSessionProperty(Constants.KEY_SERVER_STATE,
								null);
					} else {
						WebPreferences pref = Activator.getPreferences(project);
						String dec = NLS.bind(" " + Strings.DEC_LABEL_FMT, pref
								.getWebPortNo());
						decoration.addSuffix(dec);
						decoration.addOverlay(Images.RUNNING,
								IDecoration.BOTTOM_RIGHT);
					}
				}
			}
		} catch (CoreException e) {
		}
	}
}
