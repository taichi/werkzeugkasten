package werkzeugkasten.nlsgen;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import werkzeugkasten.common.runtime.AdaptableUtil;

public class ResourceGeneratorDesc {

	protected IConfigurationElement conf;

	protected ResourceGenerator rg;

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
			if (this.rg == null) {
				this.rg = AdaptableUtil.to(this.conf
						.createExecutableExtension("class"),
						ResourceGenerator.class);
			}
			return this.rg;
		} catch (CoreException e) {
			Activator.log(e);
			return null;
		}
	}
}
