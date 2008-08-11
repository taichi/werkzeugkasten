/*
 * Copyright 2008 the Seasar Foundation and the Others.
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
package werkzeugkasten.resource.synchronizer;

import java.io.File;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.seasar.eclipse.common.resource.LogUtil;
import org.seasar.eclipse.common.ui.ImageLoader;

import werkzeugkasten.resource.synchronizer.action.ToggleServerAction;
import werkzeugkasten.resource.synchronizer.nls.Images;
import werkzeugkasten.resource.synchronizer.server.JettyLauncher;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.seasar.resource.synchronizer";

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
		if (file.exists() == false) {
			file.mkdirs();
		}
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

	public static IDialogSettings getSettings() {
		return getDefault().getDialogSettings();
	}
}
