package werkzeugkasten.dirbuildpath.viewer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
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
			IProject p = c.getProject();
			if (p != null) { // IWorkspaceRootの場合、project参照をとる事が出来ない。
				IPreferenceStore pref = new ScopedPreferenceStore(
						new ProjectScope(p), Constants.ID_PLUGIN);
				String is = pref.getString(c.getFullPath().toString());
				if (StringUtil.isEmpty(is) == false && Boolean.parseBoolean(is)) {
					decoration.addOverlay(Images.AS_BUILDPATH,
							IDecoration.BOTTOM_LEFT);
				}
			}
		}
	}
}
