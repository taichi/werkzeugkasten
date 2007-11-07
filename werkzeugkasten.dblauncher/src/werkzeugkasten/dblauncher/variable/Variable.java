package werkzeugkasten.dblauncher.variable;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ClasspathVariableInitializer;
import org.osgi.framework.Bundle;

import werkzeugkasten.common.jdt.VariableUtil;
import werkzeugkasten.dblauncher.Activator;

public class Variable extends ClasspathVariableInitializer {

	public static final IPath LIB = new Path("H2_LIB");
	public static final IPath SRC = new Path("H2_SRC");

	private static final Map<String, Pattern> VARIABLES = new HashMap<String, Pattern>();

	static {
		VARIABLES.put(LIB.toString(), Pattern
				.compile("lib/h2\\-\\d{4}\\-\\d{2}\\-\\d{2}\\.jar"));
		VARIABLES.put(SRC.toString(), Pattern
				.compile("lib/h2\\-\\d{4}\\-\\d{2}\\-\\d{2}\\.src\\.jar"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(String variable) {
		if ("H2".equals(variable)) {
			for (String v : VARIABLES.keySet()) {
				Pattern p = VARIABLES.get(v);
				Bundle bundle = Activator.getDefault().getBundle();
				for (Enumeration e = bundle.getEntryPaths("/lib"); e
						.hasMoreElements();) {
					String s = e.nextElement().toString();
					if (p.matcher(s).matches()) {
						URL u = bundle.getEntry(s);
						VariableUtil.set(v, u);
					}
				}
			}
		}
	}
}
