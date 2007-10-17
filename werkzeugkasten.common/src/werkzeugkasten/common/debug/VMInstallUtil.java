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
package werkzeugkasten.common.debug;

import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.IVMInstall2;
import org.eclipse.jdt.launching.IVMInstallType;
import org.eclipse.jdt.launching.JavaRuntime;

/**
 * @author taichi
 * 
 */
public final class VMInstallUtil {

	/**
	 * 
	 */
	private VMInstallUtil() {
		super();
	}

	public static IVMInstall findMatchingJREInstall(String compliance) {
		IVMInstallType[] installTypes = JavaRuntime.getVMInstallTypes();
		for (int i = 0; i < installTypes.length; i++) {
			IVMInstall[] installs = installTypes[i].getVMInstalls();
			for (int k = 0; k < installs.length; k++) {
				if (hasMatchingCompliance(installs[k], compliance)) {
					return installs[k];
				}
			}
		}
		return null;
	}

	public static boolean hasMatchingCompliance(IVMInstall inst,
			String compliance) {
		if (!(inst instanceof IVMInstall2))
			return false;

		String version = ((IVMInstall2) inst).getJavaVersion();
		if (version != null && version.startsWith(compliance)) {
			return true;
		}
		return false;
	}

}
