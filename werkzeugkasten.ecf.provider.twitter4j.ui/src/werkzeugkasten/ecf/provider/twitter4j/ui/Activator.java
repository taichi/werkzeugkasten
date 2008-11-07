package werkzeugkasten.ecf.provider.twitter4j.ui;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.ecf.ui.util.PasswordCacheHelper;
import org.osgi.framework.BundleContext;

import werkzeugkasten.common.runtime.LogUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "werkzeugkasten.ecf.provider.twitter4j.ui";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static final String PREFIX_UID = "http://www.twitter.com/";

	public static void savePassword(String userid, String password) {
		PasswordCacheHelper cache = new PasswordCacheHelper(PREFIX_UID + userid);
		cache.savePassword(password);
	}

	public static String retrievePassword(String userid) {
		PasswordCacheHelper cache = new PasswordCacheHelper(PREFIX_UID + userid);
		return cache.retrievePassword();
	}

	public static void log(Throwable t) {
		LogUtil.log(getDefault(), t);
	}

}
