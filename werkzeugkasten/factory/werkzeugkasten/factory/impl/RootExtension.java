package werkzeugkasten.factory.impl;

import werkzeugkasten.factory.AdapterManager;
import werkzeugkasten.factory.Extension;

public class RootExtension implements Extension {

	protected AdapterManager manager;

	public RootExtension(AdapterManager manager) {
		this.manager = manager;
	}

	public String id() {
		return "werkzeugkasten.factory";
	}

	public <T> T getAdapter(Class<T> adapterType) {
		return this.manager.getAdapter(this, adapterType);
	}

}
