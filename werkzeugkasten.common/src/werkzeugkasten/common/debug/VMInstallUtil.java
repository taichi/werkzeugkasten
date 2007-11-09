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
		if (!(inst instanceof IVMInstall2)) {
			return false;
		}

		String version = ((IVMInstall2) inst).getJavaVersion();
		if (version != null && version.startsWith(compliance)) {
			return true;
		}
		return false;
	}

}
