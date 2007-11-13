package werkzeugkasten.common.jdt;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import werkzeugkasten.common.util.ArrayUtil;

public class ClasspathEntryUtil {

	public static boolean hasClasspathEntry(IJavaProject project, IPath id)
			throws CoreException {
		if (project == null) {
			return false;
		}
		Map<IPath, IClasspathEntry> map = toClasspathMap(project);
		return map.containsKey(id);
	}

	public static void addClasspathContainer(IJavaProject project, IPath id)
			throws CoreException {
		addClasspathEntry(project, JavaCore.newContainerEntry(id));
	}

	public static void addClasspathEntry(IJavaProject project,
			IClasspathEntry entry) throws CoreException {
		if (hasClasspathEntry(project, entry.getPath()) == false) {
			IClasspathEntry[] entries = project.getRawClasspath();
			entries = (IClasspathEntry[]) ArrayUtil.add(entries, entry);
			project.setRawClasspath(entries, null);
		}
	}

	public static void removeClasspathEntry(IJavaProject project, IPath id)
			throws CoreException {
		Map<IPath, IClasspathEntry> map = toClasspathMap(project);
		IClasspathEntry entry = map.get(id);
		if (entry != null) {
			IClasspathEntry[] entries = project.getRawClasspath();
			entries = (IClasspathEntry[]) ArrayUtil.remove(entries, entry);
			project.setRawClasspath(entries, null);
		}
	}

	public static Map<IPath, IClasspathEntry> toClasspathMap(IJavaProject p)
			throws CoreException {
		Map<IPath, IClasspathEntry> map = new LinkedHashMap<IPath, IClasspathEntry>();
		if (p != null) {
			IClasspathEntry[] entries = p.getRawClasspath();
			for (int i = 0; i < entries.length; i++) {
				map.put(entries[i].getPath(), entries[i]);
			}
		}
		return map;
	}
}
