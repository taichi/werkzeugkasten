/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package werkzeugkasten.dblauncher.preferences.impl;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.dblauncher.Activator;
import werkzeugkasten.dblauncher.Constants;
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
		store = new ScopedPreferenceStore(new ProjectScope(project),
				Constants.ID_PLUGIN);
		setupPreferences(project, store);
	}

	public static void setupPreferences(IProject project, IPreferenceStore store) {
		String baseDir = store.getString(Constants.PREF_BASE_DIR);
		if (StringUtil.isEmpty(baseDir)) {
			store.setValue(Constants.PREF_BASE_DIR, getDefaultBaseDir(project));
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
		return store.getString(Constants.PREF_BASE_DIR);
	}

	public void setBaseDir(String path) {
		this.store.setValue(Constants.PREF_BASE_DIR, path);
	}

	public String getDbPortNo() {
		return this.store.getString(Constants.PREF_DB_PORTNO);
	}

	public void setDbPortNo(String no) {
		this.store.setValue(Constants.PREF_DB_PORTNO, no);
	}

	public String getWebPortNo() {
		return this.store.getString(Constants.PREF_WEB_PORTNO);
	}

	public void setWebPortNo(String no) {
		this.store.setValue(Constants.PREF_WEB_PORTNO, no);
	}

	public boolean isDebug() {
		return this.store.getBoolean(Constants.PREF_IS_DEBUG);
	}

	public void setDebug(boolean is) {
		this.store.setValue(Constants.PREF_IS_DEBUG, is);
	}

	public String getPassword() {
		return this.store.getString(Constants.PREF_PASSWORD);
	}

	public String getUser() {
		return this.store.getString(Constants.PREF_USER);
	}

	public void setPassword(String pass) {
		this.store.setValue(Constants.PREF_PASSWORD, pass);
	}

	public void setUser(String user) {
		this.store.setValue(Constants.PREF_USER, user);
	}

}
