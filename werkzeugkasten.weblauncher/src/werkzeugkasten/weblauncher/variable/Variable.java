package werkzeugkasten.weblauncher.variable;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ClasspathVariableInitializer;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.jdt.VariableUtil;
import werkzeugkasten.weblauncher.Activator;

public class Variable extends ClasspathVariableInitializer {

	public static final IPath SERVLET_API_2_5 = new Path("SERVLET_API_2_5");
	public static final IPath JSP_API_2_1 = new Path("JSP_API_2_1");

	protected static Set<String> VARIABLES = new HashSet<String>();
	static {
		VARIABLES.add(SERVLET_API_2_5.toString());
		VARIABLES.add(JSP_API_2_1.toString());
	}

	@Override
	public void initialize(String variable) {
		if (VARIABLES.contains(variable)) {
			set(SERVLET_API_2_5, "/lib/servlet-api_2.5.jar");
			set(JSP_API_2_1, "/lib/jsp-api_2.1.jar");
		}
	}

	protected void set(IPath v, String path) {
		Bundle bundle = Activator.getDefault().getBundle();
		URL u = bundle.getEntry(path);
		VariableUtil.set(v.toString(), u);
	}

}
