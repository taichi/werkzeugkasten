package werkzeugkasten.dirbuildpath;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.dirbuildpath.job.AddDirClasspathJob;

public class DirClasspathResourceChangeListener implements
		IResourceChangeListener {

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		try {
			event.getDelta().accept(new IResourceDeltaVisitor() {
				protected IProject current;
				protected ScopedPreferenceStore currentPref;

				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					IResource r = delta.getResource();
					switch (r.getType()) {
					case IResource.PROJECT: {
						IProject p = r.getProject();
						this.current = p;
						this.currentPref = new ScopedPreferenceStore(
								new ProjectScope(p), Constants.ID_PLUGIN);
						return true;
					}
					case IResource.FOLDER: {
						IPath path = r.getFullPath();
						String s = this.currentPref.getString(path.toString());
						if (StringUtil.isEmpty(s) == false
								&& Boolean.parseBoolean(s)) {
							new AddDirClasspathJob(JavaCore.create(this.current),
									this.currentPref, path).schedule();
						}
						return false;
					}
					}
					return true;
				}
			});
		} catch (CoreException e) {
			Activator.log(e);
		}
	}
}