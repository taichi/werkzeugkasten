package werkzeugkasten.dblauncher.nls;

import werkzeugkasten.common.ui.ImageLoader;
import werkzeugkasten.dblauncher.Activator;

public class Images {

	static {
		Class<?> clazz = Images.class;
		ImageLoader.load(Activator.getDefault().getImageRegistry(), clazz);
	}

}
