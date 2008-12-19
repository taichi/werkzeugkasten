package werkzeugkasten.twowaysql;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import werkzeugkasten.common.runtime.LogUtil;
import werkzeugkasten.twowaysql.editor.TwoWaySqlDocumentProvider;
import werkzeugkasten.twowaysql.editor.conf.ColorManager;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "werkzeugkasten.twowaysql.plugin";

	// The shared instance
	private static Activator plugin;

	private TwoWaySqlDocumentProvider documentProvider;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		ColorManager.getDefault().dispose();
		getGlobalPreference().removePropertyChangeListener(documentProvider);
		plugin = null;
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

	public static IPreferenceStore getGlobalPreference() {
		return getDefault().getPreferenceStore();
	}

	public static TwoWaySqlDocumentProvider getDocumentProvider() {
		Activator a = getDefault();
		if (a.documentProvider == null) {
			a.documentProvider = new TwoWaySqlDocumentProvider();
			getGlobalPreference().addPropertyChangeListener(a.documentProvider);
		}
		return a.documentProvider;
	}

	public static void log(Throwable t) {
		LogUtil.log(getDefault(), t);
	}
}
