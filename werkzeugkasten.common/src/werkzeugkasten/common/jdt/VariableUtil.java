package werkzeugkasten.common.jdt;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class VariableUtil {

	public static void set(String variable, URL installLocation) {
		URL local = null;
		try {
			local = FileLocator.toFileURL(installLocation);
		} catch (IOException e) {
			JavaCore.removeClasspathVariable(variable, null);
			return;
		}
		try {
			String fullPath = new File(local.getPath()).getAbsolutePath();
			JavaCore.setClasspathVariable(variable,
					Path.fromOSString(fullPath), null);
		} catch (JavaModelException e1) {
			JavaCore.removeClasspathVariable(variable, null);
		}
	}

}
