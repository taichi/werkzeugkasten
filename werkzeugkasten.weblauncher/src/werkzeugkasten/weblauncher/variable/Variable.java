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

	public static final IPath SERVLET_API_3_0 = new Path("SERVLET_API_3_0");
	public static final IPath JSP_API_2_2 = new Path("JSP_API_2_2");

	public static final IPath SERVLET_API_2_5 = new Path("SERVLET_API_2_5");
	public static final IPath JSP_API_2_1 = new Path("JSP_API_2_1");

	public static final IPath SERVLET_API_2_4 = new Path("SERVLET_API_2_4");
	public static final IPath JSP_API_2_0 = new Path("JSP_API_2_0");

	protected static Set<String> VARIABLES = new HashSet<String>();
	static {
		VARIABLES.add(SERVLET_API_3_0.toString());
		VARIABLES.add(JSP_API_2_2.toString());
		VARIABLES.add(SERVLET_API_2_5.toString());
		VARIABLES.add(JSP_API_2_1.toString());
		VARIABLES.add(SERVLET_API_2_4.toString());
		VARIABLES.add(JSP_API_2_0.toString());
	}

	@Override
	public void initialize(String variable) {
		if (VARIABLES.contains(variable)) {
			set(SERVLET_API_3_0, "lib/servlet-api-3.0.jar");
			set(JSP_API_2_2, "/lib/javax.servlet.jsp_2.2.0.v201103241009.jar");
			set(SERVLET_API_2_5, "lib/servlet-api-2.5.jar");
			set(JSP_API_2_1, "/lib/jsp-api-2.1.jar");
			set(SERVLET_API_2_4, "lib/servlet-api-2.4.jar");
			set(JSP_API_2_0, "/lib/jsp-api-2.0.jar");
		}
	}

	protected void set(IPath v, String path) {
		Bundle bundle = Activator.getDefault().getBundle();
		URL u = bundle.getEntry(path);
		VariableUtil.set(v.toString(), u);
	}

}
