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
import werkzeugkasten.dirbuildpath.job.AddDirBuildpathJob;

public class DirBuildpathResourceChangeListener implements
		IResourceChangeListener {

	public void resourceChanged(IResourceChangeEvent event) {
		try {
			IResourceDelta delta = event.getDelta();
			if (delta != null) {
				delta.accept(new IResourceDeltaVisitor() {
					protected IProject current;
					protected ScopedPreferenceStore currentPref;

					public boolean visit(IResourceDelta delta)
							throws CoreException {
						IResource r = delta.getResource();
						switch (r.getType()) {
						case IResource.PROJECT: {
							IProject p = r.getProject();
							if (p.exists()) {
								this.current = p;
								this.currentPref = new ScopedPreferenceStore(
										new ProjectScope(p),
										Constants.ID_PLUGIN);
								return true;
							}
							return false;
						}
						case IResource.FOLDER: {
							IPath path = r.getFullPath();
							String s = this.currentPref.getString(path
									.toString());
							if (this.current != null
									&& StringUtil.isEmpty(s) == false
									&& Boolean.parseBoolean(s)) {
								new AddDirBuildpathJob(JavaCore
										.create(this.current),
										this.currentPref, path).schedule();
							}
							return false;
						}
						}
						return true;
					}
				});
			}
		} catch (CoreException e) {
			Activator.log(e);
		}
	}
}
