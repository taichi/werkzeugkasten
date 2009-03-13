package werkzeugkasten.common.jdt;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;

/**
 * @author taichi
 */
public abstract class JavaProjectClassLoader extends URLClassLoader {

	public JavaProjectClassLoader(IJavaProject project) {
		super(new URL[0]);
		Set<IJavaProject> already = new HashSet<IJavaProject>();
		addClasspathEntries(project, already, true);
	}

	public JavaProjectClassLoader(IJavaProject project, ClassLoader parent) {
		super(new URL[0], parent);
		Set<IJavaProject> already = new HashSet<IJavaProject>();
		addClasspathEntries(project, already, true);
	}

	protected void addClasspathEntries(IJavaProject project,
			Set<IJavaProject> already, boolean atFirst) {
		already.add(project);

		try {
			IContainer workspaceroot = project.getProject().getParent();
			IPath path = project.getOutputLocation();
			add(workspaceroot.getFolder(path).getLocation());

			IClasspathEntry[] entries = project.getResolvedClasspath(true);
			for (IClasspathEntry entry : entries) {
				switch (entry.getEntryKind()) {
				case IClasspathEntry.CPE_SOURCE:
					IPath dist = entry.getOutputLocation();
					if (dist != null) {
						add(workspaceroot.getFolder(dist).getLocation());
					}
					break;
				case IClasspathEntry.CPE_LIBRARY:
				case IClasspathEntry.CPE_CONTAINER:
				case IClasspathEntry.CPE_VARIABLE:
					IPath p = entry.getPath();
					if (p.toFile().exists()) {
						add(p);
					} else {
						add(workspaceroot.getFile(p).getLocation());
					}
					break;
				case IClasspathEntry.CPE_PROJECT:
					IJavaProject proj = JavaElementUtil.getJavaProject(entry
							.getPath().segment(0));
					if (proj != null && proj.exists()
							&& already.contains(proj) == false
							&& (atFirst || entry.isExported())) {
						addClasspathEntries(proj, already, false);
					}
					break;
				default:
					break;
				}
			}
		} catch (JavaModelException e) {
			log(e);
		}
	}

	protected void add(IPath path) {
		try {
			addURL(path.toFile().getAbsoluteFile().toURI().toURL());
		} catch (MalformedURLException e) {
			log(e);
		}
	}

	public abstract void log(Throwable t);
}
