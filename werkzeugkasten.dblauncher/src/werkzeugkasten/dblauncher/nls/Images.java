package werkzeugkasten.dblauncher.nls;

import org.eclipse.jface.resource.ImageDescriptor;

import werkzeugkasten.common.ui.ImageLoader;
import werkzeugkasten.dblauncher.Activator;

public class Images {

	public static ImageDescriptor RUNNING;

	public static ImageDescriptor START;

	public static ImageDescriptor STOP;

	public static ImageDescriptor EXECUTE;

	public static ImageDescriptor TERMINATE;

	static {
		Class<?> clazz = Images.class;
		ImageLoader.load(Activator.getDefault().getImageRegistry(), clazz);
	}

}
