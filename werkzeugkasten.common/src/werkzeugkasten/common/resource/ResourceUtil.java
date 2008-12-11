package werkzeugkasten.common.resource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.runtime.LogUtil;
import werkzeugkasten.common.util.StringUtil;

public class ResourceUtil {

	public static final String PATH_QUOTATION;
	static {
		String os = System.getProperty("osgi.os");
		if ("win32".equals(os)) {
			PATH_QUOTATION = "\"";
		} else if ("macosx".equals(os)) {
			PATH_QUOTATION = "'";
		} else {
			PATH_QUOTATION = "\"";
		}
	}

	public static String quote(String path) {
		if (StringUtil.isEmpty(path)) {
			return "";
		}
		if (0 < path.indexOf(" ")) {
			return PATH_QUOTATION + path + PATH_QUOTATION;
		}
		return path;
	}

	public static IFile findFile(final String name, IContainer root) {
		final IFile[] file = new IFile[1];
		try {
			IResourceVisitor visitor = new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					if (name.equalsIgnoreCase(resource.getName())
							&& resource instanceof IFile) {
						file[0] = (IFile) resource;
						return false;
					}
					return true;
				}
			};
			root.accept(visitor);
		} catch (CoreException e) {
			LogUtil.log(ResourcesPlugin.getPlugin(), e);
		}
		return file[0];
	}

	public static void createDir(IContainer container, String path) {
		try {
			IPath fullpath = new Path(path);
			if (container.exists(fullpath) == false) {
				String[] ary = fullpath.segments();
				StringBuffer stb = new StringBuffer();
				for (int i = 0; i < ary.length; i++) {
					IPath p = new Path(stb.append(ary[i]).toString());
					if (container.exists(p) == false) {
						IFolder f = container.getFolder(p);
						f.create(true, true, null);
					}
					stb.append('/');
				}
			}
		} catch (CoreException e) {
			LogUtil.log(ResourcesPlugin.getPlugin(), e);
		}
	}

	public static void copyFile(IContainer container, URL u)
			throws CoreException, IOException {
		IPath p = new Path(u.getFile());
		String s = p.lastSegment();
		IFile newone = container.getFile(new Path(s));
		if (newone != null && newone.exists() == false) {
			newone.create(new BufferedInputStream(u.openStream()), true, null);
		}
	}

	public static Map<IProject, List<IPath>> toProjectPathMap(Iterator<?> i) {
		Map<IProject, List<IPath>> map = new LinkedHashMap<IProject, List<IPath>>();
		while (i.hasNext()) {
			IContainer c = AdaptableUtil.to(i.next(), IContainer.class);
			if (c != null) {
				IProject p = c.getProject();
				List<IPath> list = map.get(p);
				if (list == null) {
					list = new ArrayList<IPath>();
					map.put(p, list);
				}
				list.add(c.getFullPath());
			}
		}
		return map;
	}

}
