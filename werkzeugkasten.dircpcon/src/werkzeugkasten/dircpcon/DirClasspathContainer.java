package werkzeugkasten.dircpcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.osgi.util.NLS;

import werkzeugkasten.common.jdt.ClasspathEntryUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;
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
		this.dir = path.removeFirstSegments(2).toString();
		this.project = project;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(this,
				IResourceChangeEvent.PRE_DELETE
						| IResourceChangeEvent.POST_CHANGE);
	}

	public IClasspathEntry[] getClasspathEntries() {
		if (this.entries == null) {
			this.entries = computeEntries();
		}
		return this.entries.values().toArray(
				new IClasspathEntry[this.entries.size()]);
	}

	protected static final Pattern isLib = Pattern.compile(".+\\.(zip|jar)",
			Pattern.CASE_INSENSITIVE);
	protected static final Pattern isSrc = Pattern.compile(".*(src|sources).*",
			Pattern.CASE_INSENSITIVE);

	protected Map<String, IClasspathEntry> computeEntries() {
		final Map<String, IClasspathEntry> result = new TreeMap<String, IClasspathEntry>();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource rootDir = root.findMember(this.path);
		IContainer c = AdaptableUtil.to(rootDir, IContainer.class);
		if (c != null) {
			try {
				c.accept(new IResourceVisitor() {
					@Override
					public boolean visit(IResource resource)
							throws CoreException {
						IPath full = resource.getFullPath();
						if (isSrc.matcher(full.toString()).matches() == false
								&& resource.getType() == IResource.FILE
								&& isLib.matcher(full.lastSegment()).matches()) {
							IPath src = findSource(full);
							IClasspathEntry ce = JavaCore.newLibraryEntry(full,
									src, new Path("."));
							result.put(full.toString(), ce);
						}
						return true;
					}
				});
			} catch (CoreException e) {
				Activator.log(e);
			}
		}
		return result;
	}

	protected IPath findSource(IPath lib) {
		String[] fix = { "src", "sources" };
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		for (IPath parent : parents(fix, lib)) {
			for (IPath name : names(fix, lib)) {
				IResource r = root.findMember(parent.append(name));
				if (r != null && r.exists()) {
					return r.getFullPath();
				}
			}
		}
		return null;
	}

	protected List<IPath> parents(String[] sufix, IPath lib) {
		IPath parent = lib.removeLastSegments(1);
		List<IPath> parents = new ArrayList<IPath>();
		parents.add(parent);
		for (String s : sufix) {
			parents.add(parent.append(s));
		}
		return parents;
	}

	protected List<IPath> names(String[] sufix, IPath lib) {
		String base = lib.removeFileExtension().lastSegment();
		String[] sign = { ".", "-" };
		String[] ext = { "jar", "zip" };
		List<IPath> names = new ArrayList<IPath>();
		for (String s : sign) {
			String signed = base + s;
			for (String f : sufix) {
				String fixed = signed + f;
				for (String e : ext) {
					IPath filename = new Path(fixed).addFileExtension(e);
					names.add(filename);
				}
			}
		}
		return names;
	}

	public String getDescription() {
		return NLS.bind(Strings.DESC_DIR_CLASSPATH_CONTAINER, this.dir);
	}

	public int getKind() {
		return K_APPLICATION;
	}

	public IPath getPath() {
		return ID.append(this.path);
	}

	// <classpathentry kind="con"
	// path="werkzeugkasten.dircpcon.DIR_CLASSPATH/webproject/webapp"/>

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {
			delta.accept(new IResourceDeltaVisitor() {
				@Override
				public boolean visit(IResourceDelta delta) throws CoreException {
					final IResource r = delta.getResource();
					IPath changed = r.getFullPath().makeRelative();
					if (changed.equals(DirClasspathContainer.this.path)) {
						new WorkspaceJob(Strings.UPDATE_CLASSPATH_CONTAINER) {
							@Override
							public IStatus runInWorkspace(
									IProgressMonitor monitor)
									throws CoreException {
								IJavaProject p = JavaCore
										.create(r.getProject());
								IPath cppath = DirClasspathContainer.this
										.getPath();
								ClasspathEntryUtil.removeClasspathEntry(p,
										cppath);
								ResourcesPlugin.getWorkspace()
										.removeResourceChangeListener(
												DirClasspathContainer.this);
								ClasspathEntryUtil.addClasspathContainer(p,
										cppath);
								return Status.OK_STATUS;
							}
						}.schedule();
					}
					return true;
				}
			});
		} catch (CoreException e) {
			Activator.log(e);
		}
	}

}
