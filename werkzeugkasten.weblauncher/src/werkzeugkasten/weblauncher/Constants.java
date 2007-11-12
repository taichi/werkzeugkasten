package werkzeugkasten.weblauncher;

import org.eclipse.core.runtime.QualifiedName;

/**
 * 
 * @author ryushi
 * 
 */
public final class Constants {

	public static final String ID_PLUGIN = "org.seasar.weblauncher";

	public static final String ID_NATURE = ID_PLUGIN + ".nature";

	public static final String ID_DECORATOR = ID_PLUGIN + ".decorator";

	public static final String ID_LAUNCH_CONFIG = ID_PLUGIN
			+ ".launchConfigurationType";

	public static final String ID_BROWSER = ID_PLUGIN + ".browser";

	/* ---------------------------------------------------------------------- */
	public static final QualifiedName KEY_SERVER_STATE = new QualifiedName(
			ID_PLUGIN, "serverstate");

	public static final QualifiedName KEY_JOB_PROCESSING = new QualifiedName(
			ID_PLUGIN, "jobstarted");

	public static final QualifiedName KEY_CHECK_SERVER = new QualifiedName(
			ID_PLUGIN, "checkserver");

	/* ---------------------------------------------------------------------- */
	public static final String PREF_CONTEXT_NAME = "contextName";

	public static final String PREF_BASE_DIR = "baseDir";

	public static final String PREF_WEB_PORTNO = "httpPort";

	public static final String PREF_IS_DEBUG = "isDebug";

	public static final String PREF_CONFIG = "configfile";

	public static final String PREF_CHECK_SERVER = "checkServer";

	public static final String PREF_USE_INTERNAL_WEBBROWSER = "useInternalWebBrowser";

	public static final String PREF_WEB_SERVER_TYPE = "webType";

	/* ---------------------------------------------------------------------- */
	public static final String EXT_LAUNCHCONFIG_FACET = "launchConfigurationFacet";

}
