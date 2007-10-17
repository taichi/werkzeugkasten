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
package werkzeugkasten.common.resource;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * @author taichi
 * 
 */
public class LogUtil {

	public static void log(Plugin plugin, Throwable throwable) {
		IStatus status = null;
		if (plugin == null) {
			plugin = ResourcesPlugin.getPlugin();
		}
		if (throwable instanceof CoreException) {
			CoreException e = (CoreException) throwable;
			status = e.getStatus();
		} else {
			status = StatusUtil.createError(plugin, Status.ERROR, throwable);
		}
		plugin.getLog().log(status);
	}

	public static void log(Plugin plugin, String msg) {
		IStatus status = StatusUtil.createInfo(plugin, Status.INFO, msg, null);
		plugin.getLog().log(status);
	}

}
