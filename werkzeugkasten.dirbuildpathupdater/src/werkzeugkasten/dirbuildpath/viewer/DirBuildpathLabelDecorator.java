package werkzeugkasten.dirbuildpath.viewer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.viewers.AbstractLightweightLabelDecorator;
import werkzeugkasten.dirbuildpath.Constants;
import werkzeugkasten.dirbuildpath.nls.Images;

public class DirBuildpathLabelDecorator extends
		AbstractLightweightLabelDecorator {

	@Override
	public void decorate(Object element, IDecoration decoration) {
		IContainer c = AdaptableUtil.to(element, IContainer.class);
		if (c != null) {
			IPath p = c.getFullPath();
			IPreferenceStore pref = new ScopedPreferenceStore(new ProjectScope(
					c.getProject()), Constants.ID_PLUGIN);
			String is = pref.getString(p.toString());
			if (StringUtil.isEmpty(is) == false && Boolean.parseBoolean(is)) {
				decoration.addOverlay(Images.AS_BUILDPATH,
						IDecoration.BOTTOM_LEFT);
			}
		}
	}
}
