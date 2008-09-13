package werkzeugkasten.weblauncher.preferences;

import java.util.regex.Pattern;

/**
 * @author ryushi
 */
public interface WebPreferences {

	String getWebServerType();

	void setWebServerType(String type);

	String getLibraryType();

	void setLibraryType(String type);

	String getContextName();

	void setContextName(String name);

	String getBaseDir();

	void setBaseDir(String path);

	String getWebPortNo();

	void setWebPortNo(String no);

	void setDebug(boolean is);

	boolean isDebug();

	void setConfig(String path);

	String getConfig();

	boolean checkServerWhenOpen();

	void setCheckServerWhenOpen(boolean is);

	boolean useInternalWebBrowser();

	void setInternalWebBrowser(boolean is);

	Pattern getExportIgnore();

	void setExportIgnore(String fmt);
}
