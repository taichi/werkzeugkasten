package werkzeugkasten.webappnls.gen;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.JavaCore;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.webappnls.Activator;
import werkzeugkasten.webappnls.Constants;
import werkzeugkasten.webappnls.ResourceGenerator;

public class MultiLocaleStrings implements ResourceGenerator {

	@Override
	public void generateFrom(IResource resource) {
		try {
			String s = resource
					.getPersistentProperty(Constants.GENERATION_DEST);
			IPath path = null;
			if (StringUtil.isEmpty(s)) {
				IPath p = resource.getFullPath();
				path = p.removeFileExtension().addFileExtension("java");
			} else {
				path = new Path(s);
			}
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IResource dest = root.findMember(path);
			IJavaElement element = JavaCore.create(dest);
			if (element != null) {
				if (element.exists()) {
				} else {
				}
			}

		} catch (CoreException e) {
			Activator.log(e);
		}
	}

}
