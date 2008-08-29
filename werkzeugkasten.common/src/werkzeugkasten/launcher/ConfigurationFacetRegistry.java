package werkzeugkasten.launcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import werkzeugkasten.common.runtime.ExtensionAcceptor;
import werkzeugkasten.common.runtime.LogUtil;

public class ConfigurationFacetRegistry {

	protected final AtomicBoolean initialized = new AtomicBoolean(false);

	protected Map<String, ConfigurationFacet> facets = new HashMap<String, ConfigurationFacet>();

	protected final String namespace;
	protected final String extensionPointName;

	public ConfigurationFacetRegistry(String namespace,
			String extensionPointName) {
		if (namespace == null) {
			throw new IllegalArgumentException("namespace is null");
		}
		if (extensionPointName == null) {
			throw new IllegalArgumentException("extensionPointName is null");
		}
		this.namespace = namespace;
		this.extensionPointName = extensionPointName;
	}

	public ConfigurationFacet find(String key) {
		synchronized (initialized) {
			if (initialized.compareAndSet(false, true)) {
				initialize();
			}
			return facets.get(key);
		}
	}

	public Set<String> keys() {
		synchronized (initialized) {
			if (initialized.compareAndSet(false, true)) {
				initialize();
			}
			return new HashSet<String>(this.facets.keySet());
		}
	}

	protected void initialize() {
		ExtensionAcceptor.accept(this.namespace, this.extensionPointName,
				new ExtensionAcceptor.ExtensionVisitor() {
					public boolean visit(IConfigurationElement e) {
						if (extensionPointName.equals(e.getName())) {
							try {
								Object o = e.createExecutableExtension("class");
								if (o instanceof ConfigurationFacet) {
									ConfigurationFacet facet = (ConfigurationFacet) o;
									facets.put(facet.getType(), facet);
								}
							} catch (CoreException ex) {
								LogUtil.log(ResourcesPlugin.getPlugin(), ex);
							}
						}
						return true;
					}
				});
	}

	public void dispose() {
		synchronized (initialized) {
			if (initialized.compareAndSet(true, false)) {
				this.facets.clear();
			}
		}
	}
}
