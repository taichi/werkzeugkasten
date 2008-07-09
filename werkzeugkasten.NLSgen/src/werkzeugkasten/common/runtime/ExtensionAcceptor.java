package werkzeugkasten.common.runtime;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * @author taichi
 * 
 */
public class ExtensionAcceptor {

	public static void accept(String namespace, String extensionPointName,
			ExtensionVisitor visitor) {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint point = registry.getExtensionPoint(namespace,
				extensionPointName);
		IExtension[] extensions = point.getExtensions();
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement[] elements = extensions[i]
					.getConfigurationElements();
			for (int j = 0; j < elements.length; j++) {
				if (visitor.visit(elements[j]) == false) {
					return;
				}
			}
		}

	}

	public interface ExtensionVisitor {
		boolean visit(IConfigurationElement e);
	}

}
