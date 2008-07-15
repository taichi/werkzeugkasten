package werkzeugkasten.dircpcon;

import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.osgi.util.NLS;

import werkzeugkasten.dircpcon.nls.Strings;

public class DirClasspathContainer implements IClasspathContainer,
		IResourceChangeListener {

	public static final IPath ID = new Path(
			Constants.ID_DIR_CLASSPATH_CONTAINER);

	protected IPath path;

	protected String dir;

	protected IJavaProject project;

	protected Map<String, IClasspathEntry> entries;

	public DirClasspathContainer(IPath path, IJavaProject project) {
		this.path = path.removeFirstSegments(1);
		this.project = project;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(this);
	}

	public IClasspathEntry[] getClasspathEntries() {
		if (this.entries == null) {
			this.entries = computeEntries();
		}
		return this.entries.values().toArray(
				new IClasspathEntry[this.entries.size()]);
	}

	protected Map<String, IClasspathEntry> computeEntries() {
		return null;
	}

	public String getDescription() {
		return NLS.bind(Strings.DESC_DIR_CLASSPATH_CONTAINER, this.dir);
	}

	public int getKind() {
		return K_APPLICATION;
	}

	public IPath getPath() {
		return ID;
	}

	public void resourceChanged(IResourceChangeEvent event) {
		IResource r = event.getResource();
		IPath changed = r.getLocation();
		if (changed.isPrefixOf(this.path) && this.entries != null) {
			this.entries.clear();
			this.entries = null;
		}
	}

}
