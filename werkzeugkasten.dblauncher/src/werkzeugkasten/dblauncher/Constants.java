package werkzeugkasten.dblauncher;

import org.eclipse.core.runtime.QualifiedName;

public final class Constants {

	public static final String ID_PLUGIN = "werkzeugkasten.dblauncher";

	public static final String ID_NATURE = ID_PLUGIN + ".nature";

	public static final String ID_DECORATOR = ID_PLUGIN + ".decorator";

	public static final String ID_H2_LAUNCH_CONFIG = ID_PLUGIN
			+ ".launchConfigurationType";

	/* ---------------------------------------------------------------------- */
	public static final QualifiedName KEY_SERVER_STATE = new QualifiedName(
			ID_PLUGIN, "serverstate");
	public static final QualifiedName KEY_JOB_PROCESSING = new QualifiedName(
			ID_PLUGIN, "jobstarted");

	/* ---------------------------------------------------------------------- */
	public static final String PREF_BASE_DIR = "baseDir";

	public static final String PREF_DB_PORTNO = "dbPortNo";

	public static final String PREF_WEB_PORTNO = "webPortNo";

	public static final String PREF_IS_DEBUG = "isDebug";

	public static final String PREF_USER = "user";

	public static final String PREF_PASSWORD = "password";

	public static final String PREF_USE_INTERNAL_WEBBROWSER = "useInternalWebBrowser";

	public static final String PREF_DB_TYPE = "dbType";

	/* ---------------------------------------------------------------------- */
	public static final String CTX_HELP_PREF = ID_PLUGIN
			+ ".preferences_page_context";

	/* ---------------------------------------------------------------------- */
	public static final String EXT_LAUNCHCONFIG_BUILDER = "launchConfigurationBuilder";
}
