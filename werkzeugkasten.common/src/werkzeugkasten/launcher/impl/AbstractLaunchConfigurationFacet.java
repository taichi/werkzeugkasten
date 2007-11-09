package werkzeugkasten.launcher.impl;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

import werkzeugkasten.launcher.LaunchConfigurationFacet;

public abstract class AbstractLaunchConfigurationFacet implements
		LaunchConfigurationFacet, IExecutableExtension {

	protected String type;

	protected String description;

	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		this.type = config.getAttribute("type");
		this.description = config.getAttribute("description");
	}

	public String getType() {
		return this.type;
	}

	public String getDescription() {
		return this.description;
	}

}
