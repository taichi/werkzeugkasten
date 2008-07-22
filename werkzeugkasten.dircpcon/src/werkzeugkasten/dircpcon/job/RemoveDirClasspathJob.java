package werkzeugkasten.dircpcon.job;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.jdt.ClasspathEntryUtil;
import werkzeugkasten.dircpcon.Activator;
import werkzeugkasten.dircpcon.Constants;
import werkzeugkasten.dircpcon.nls.Strings;

public class RemoveDirClasspathJob extends WorkspaceJob {

	protected Map<IProject, List<IPath>> map;

	public RemoveDirClasspathJob(Map<IProject, List<IPath>> map) {
		super(Strings.UPDATE_CLASSPATH_CONTAINER);
		this.map = map;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor)
			throws CoreException {
		for (IProject project : this.map.keySet()) {
			try {
				IJavaProject javap = JavaCore.create(project);
				final Map<IPath, IClasspathEntry> ents = ClasspathEntryUtil
						.toClasspathMap(javap);
				ScopedPreferenceStore store = new ScopedPreferenceStore(
						new ProjectScope(project), Constants.ID_PLUGIN);
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				for (IPath p : this.map.get(project)) {
					store.setValue(p.toString(), "");
					IResource r = root.findMember(p);
					if (r != null && r.exists()) {
						r.accept(new IResourceVisitor() {
							@Override
							public boolean visit(IResource resource)
									throws CoreException {
								if (AddDirClasspathJob.isLib.matcher(
										resource.getName()).matches()) {
									IPath path = resource.getFullPath();
									ents.remove(path);
								}
								return true;
							}
						});
					}
				}
				Collection<IClasspathEntry> c = ents.values();
				javap.setRawClasspath(c.toArray(new IClasspathEntry[c.size()]),
						null);
				store.save();
			} catch (IOException e) {
				Activator.log(e);
			}
		}
		return Status.OK_STATUS;
	}

}
