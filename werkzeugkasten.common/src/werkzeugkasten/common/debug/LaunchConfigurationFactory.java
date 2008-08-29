package werkzeugkasten.common.debug;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;

import werkzeugkasten.common.runtime.LogUtil;

/**
 * @author taichi
 * 
 */
public class LaunchConfigurationFactory {

	public static ILaunchConfiguration create(CreationHandler handler) {
		ILaunchConfiguration config = null;
		try {
			ILaunchManager manager = DebugPlugin.getDefault()
					.getLaunchManager();
			ILaunchConfigurationType type = manager
					.getLaunchConfigurationType(handler.getTypeName());
			ILaunchConfiguration[] configs = manager
					.getLaunchConfigurations(type);
			for (int i = 0; i < configs.length; i++) {
				if (configs[i].getName().equals(handler.getConfigName())) {
					if (handler.equals(configs[i])) {
						config = configs[i];
					} else {
						configs[i].delete();
					}
					break;
				}
			}
			if (config == null) {
				ILaunchConfigurationWorkingCopy copy = type.newInstance(null,
						handler.getConfigName());
				handler.setUp(copy);
				config = copy.doSave();
			}
		} catch (CoreException e) {
			LogUtil.log(ResourcesPlugin.getPlugin(), e);
		}
		return config;
	}

	public interface CreationHandler {

		String getTypeName();

		String getConfigName();

		boolean equals(ILaunchConfiguration config) throws CoreException;

		void setUp(ILaunchConfigurationWorkingCopy config) throws CoreException;
	}
}
