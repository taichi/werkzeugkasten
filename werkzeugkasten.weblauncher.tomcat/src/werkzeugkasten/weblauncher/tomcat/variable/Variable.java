package werkzeugkasten.weblauncher.tomcat.variable;

import java.net.URL;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ClasspathVariableInitializer;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.jdt.VariableUtil;
import werkzeugkasten.weblauncher.tomcat.Activator;

public class Variable extends ClasspathVariableInitializer {

	protected static final String NAME_TOMCAT_BASE = "TOMCAT_BASE";
	public static final IPath TOMCAT_BASE = new Path(NAME_TOMCAT_BASE);
	public static final IPath SERVLET_API_2_5 = new Path("SERVLET_API_2_5");
	public static final IPath JSP_API_2_1 = new Path("JSP_API_2_1");

	@Override
	public void initialize(String variable) {
		if (NAME_TOMCAT_BASE.equals(variable)) {
			set(TOMCAT_BASE, "/tomcat");
			set(SERVLET_API_2_5, "/tomcat/lib/servlet-api.jar");
			set(JSP_API_2_1, "/tomcat/lib/jsp-api.jar");
		}
	}

	protected void set(IPath v, String path) {
		Bundle bundle = Activator.getDefault().getBundle();
		URL u = bundle.getEntry(path);
		VariableUtil.set(v.toString(), u);
	}

}
