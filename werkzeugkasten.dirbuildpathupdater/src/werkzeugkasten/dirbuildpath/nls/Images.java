/**
 * 
 */
package werkzeugkasten.dirbuildpath.nls;

import org.eclipse.jface.resource.ImageDescriptor;

import werkzeugkasten.dirbuildpath.Activator;
import werkzeugkasten.nlsgen.eclipse.ImageLoader;

/**
 * @author taichi
 * 
 */
public class Images {

	public static ImageDescriptor AS_BUILDPATH;

	static {
		ImageLoader.load(Activator.getDefault(), Images.class, "images");
	}
}