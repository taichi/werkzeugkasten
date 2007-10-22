package werkzeugkasten.dblauncher.nls;

import org.eclipse.jface.resource.ImageDescriptor;

import werkzeugkasten.common.ui.ImageLoader;
import werkzeugkasten.dblauncher.Activator;

public class Images {

	public static ImageDescriptor RUNNING;

	static {
		Class<?> clazz = Images.class;
		ImageLoader.load(Activator.getDefault().getImageRegistry(), clazz);
	}

}
