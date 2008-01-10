package werkzeugkasten.synchronizer;

import java.io.File;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import werkzeugkasten.common.resource.LogUtil;
import werkzeugkasten.common.ui.ImageLoader;
import werkzeugkasten.synchronizer.action.ToggleServerAction;
import werkzeugkasten.synchronizer.nls.Images;
import werkzeugkasten.synchronizer.server.JettyLauncher;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "werkzeugkasten.synchronizer";

	// The shared instance
	private static Activator plugin;

	private JettyLauncher launcher;

	private ToggleServerAction toggleaction;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		ImageLoader.load(this, Images.class);
		File file = new File(context.getDataFile(""), "workdir");
		this.launcher = new JettyLauncher(file);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		ImageLoader.unload(this, Images.class);
		this.launcher.stop();
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public static void log(Throwable throwable) {
		LogUtil.log(getDefault(), throwable);
	}

	public static void startServer() {
		getDefault().launcher.start();
	}

	public static boolean isRunning() {
		return getDefault().launcher.isRunning();
	}

	public static void stopServer() {
		getDefault().launcher.stop();
	}

	public static void setToggleAction(ToggleServerAction action) {
		getDefault().toggleaction = action;
	}

	public static void refreshToggleAction() {
		ToggleServerAction action = getDefault().toggleaction;
		if (action != null) {
			action.checkEnabled();
		}
	}
}
