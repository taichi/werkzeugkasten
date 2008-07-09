package werkzeugkasten.nlsgen;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import werkzeugkasten.common.runtime.AdaptableUtil;

public class ResourceGeneratorDesc {

	protected IConfigurationElement conf;

	public ResourceGeneratorDesc(IConfigurationElement conf) {
		this.conf = conf;
	}

	public String getId() {
		return this.conf.getAttribute("id");
	}

	public String getLabel() {
		return this.conf.getAttribute("label");
	}

	public String getDescription() {
		return this.conf.getAttribute("desc");
	}

	public ResourceGenerator getInstance() {
		try {
			return AdaptableUtil.to(this.conf
					.createExecutableExtension("class"),
					ResourceGenerator.class);
		} catch (CoreException e) {
			Activator.log(e);
			return null;
		}
	}
}
