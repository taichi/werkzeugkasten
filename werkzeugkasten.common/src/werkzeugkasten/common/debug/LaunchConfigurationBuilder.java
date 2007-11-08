package werkzeugkasten.common.debug;

import org.eclipse.core.resources.IProject;
import org.eclipse.debug.core.ILaunchConfiguration;

public interface LaunchConfigurationBuilder {

	void setProject(IProject project);

	ILaunchConfiguration build();
}
