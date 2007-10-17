package werkzeugkasten.common.debug;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;

import werkzeugkasten.common.resource.LogUtil;
import werkzeugkasten.common.util.StringUtil;

/**
 * @author taichi
 * 
 */
public class LaunchUtil {

	public static IProject getProject(ILaunch launch) {
		IProject result = null;
		try {
			ILaunchConfiguration config = launch.getLaunchConfiguration();
			String name = config.getAttribute(
					IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "");
			if (StringUtil.isEmpty(name) == false) {
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				result = root.getProject(name);
			}
		} catch (CoreException e) {
			LogUtil.log(ResourcesPlugin.getPlugin(), e);
		}
		return result;
	}
}
