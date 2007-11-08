package werkzeugkasten.dblauncher.preferences.impl;

import static werkzeugkasten.dblauncher.Constants.*;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.debug.LaunchConfigurationBuilder;
import werkzeugkasten.common.runtime.ExtensionAcceptor;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.dblauncher.Activator;
import werkzeugkasten.dblauncher.preferences.DbPreferences;

/**
 * @author taichi
 * 
 */
public class DbPreferencesImpl implements DbPreferences {

	private ScopedPreferenceStore store;

	/**
	 * 
	 */
	public DbPreferencesImpl(IProject project) {
		super();
		store = new ScopedPreferenceStore(new ProjectScope(project), ID_PLUGIN);
		setupPreferences(project, store);
	}

	public static void setupPreferences(IProject project, IPreferenceStore store) {
		String baseDir = store.getString(PREF_BASE_DIR);
		if (StringUtil.isEmpty(baseDir)) {
			store.setValue(PREF_BASE_DIR, getDefaultBaseDir(project));
		}
	}

	public static String getDefaultBaseDir(IProject project) {
		String result = "";
		try {
			if (project != null) {
				IJavaProject jp = JavaCore.create(project);
				IPath p = jp.getOutputLocation();
				IFolder f = project.getParent().getFolder(p.append("data"));
				if (f.exists() == false) {
					f.create(true, true, null);
				}
				IPath data = f.getFullPath();
				result = data.toString();
			}
		} catch (Exception e) {
			Activator.log(e);
		}

		return result;
	}

	public String getBaseDir() {
		return store.getString(PREF_BASE_DIR);
	}

	public void setBaseDir(String path) {
		this.store.setValue(PREF_BASE_DIR, path);
	}

	public String getDbPortNo() {
		return this.store.getString(PREF_DB_PORTNO);
	}

	public void setDbPortNo(String no) {
		this.store.setValue(PREF_DB_PORTNO, no);
	}

	public String getWebPortNo() {
		return this.store.getString(PREF_WEB_PORTNO);
	}

	public void setWebPortNo(String no) {
		this.store.setValue(PREF_WEB_PORTNO, no);
	}

	public boolean isDebug() {
		return this.store.getBoolean(PREF_IS_DEBUG);
	}

	public void setDebug(boolean is) {
		this.store.setValue(PREF_IS_DEBUG, is);
	}

	public String getPassword() {
		return this.store.getString(PREF_PASSWORD);
	}

	public String getUser() {
		return this.store.getString(PREF_USER);
	}

	public void setPassword(String pass) {
		this.store.setValue(PREF_PASSWORD, pass);
	}

	public void setUser(String user) {
		this.store.setValue(PREF_USER, user);
	}

	public void setInternalWebBrowser(boolean is) {
		this.store.setValue(PREF_USE_INTERNAL_WEBBROWSER, is);
	}

	public boolean useInternalWebBrowser() {
		return this.store.getBoolean(PREF_USE_INTERNAL_WEBBROWSER);
	}

	public String getDbType() {
		return this.store.getString(PREF_DB_TYPE);
	}

	public void setDbType(String type) {
		this.store.setValue(PREF_DB_TYPE, type);
	}

	public LaunchConfigurationBuilder getBuilder() {
		final LaunchConfigurationBuilder[] result = new LaunchConfigurationBuilder[1];
		ExtensionAcceptor.accept(ID_PLUGIN, EXT_LAUNCHCONFIG_BUILDER,
				new ExtensionAcceptor.ExtensionVisitor() {
					public boolean visit(IConfigurationElement e) {
						if (EXT_LAUNCHCONFIG_BUILDER.equals(e.getName())
								&& e.getAttribute("type").equalsIgnoreCase(
										getDbType())) {
							try {
								Object o = e.createExecutableExtension("class");
								if (o instanceof LaunchConfigurationBuilder) {
									result[0] = (LaunchConfigurationBuilder) o;
									return false;
								}
							} catch (CoreException ex) {
								Activator.log(ex);
							}
						}
						return true;
					}
				});
		return result[0];
	}
}
