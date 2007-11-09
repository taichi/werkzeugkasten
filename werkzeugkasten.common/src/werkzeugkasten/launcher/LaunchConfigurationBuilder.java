package werkzeugkasten.launcher;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;

public interface LaunchConfigurationBuilder {

	void setProject(IProject project);

	ILaunchConfiguration build() throws CoreException;
}
