package werkzeugkasten.launcher;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import werkzeugkasten.common.resource.LogUtil;
import werkzeugkasten.common.runtime.ExtensionAcceptor;

public class LaunchConfigurationFacetRegistry {

	protected final AtomicBoolean initialized = new AtomicBoolean(false);

	protected Map<String, LaunchConfigurationFacet> facets = new Hashtable<String, LaunchConfigurationFacet>();

	protected final String namespace;
	protected final String extensionPointName;

	public LaunchConfigurationFacetRegistry(String namespace,
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

	public LaunchConfigurationFacet find(String key) {
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
								if (o instanceof LaunchConfigurationFacet) {
									LaunchConfigurationFacet facet = (LaunchConfigurationFacet) o;
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
