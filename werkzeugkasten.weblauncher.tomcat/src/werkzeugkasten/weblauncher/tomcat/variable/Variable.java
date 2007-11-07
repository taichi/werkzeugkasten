package werkzeugkasten.weblauncher.tomcat.variable;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ClasspathVariableInitializer;

public class Variable extends ClasspathVariableInitializer {

	protected static final String NAME_TOMCAT_BASE = "TOMCAT_BASE";
	public static final IPath TOMCAT_BASE = new Path(NAME_TOMCAT_BASE);

	@Override
	public void initialize(String variable) {
		if (NAME_TOMCAT_BASE.equals(variable)) {
			// Bundle bundle = Activator.getDefault().getBundle();
			// TODO 未実装
		}
	}

}
