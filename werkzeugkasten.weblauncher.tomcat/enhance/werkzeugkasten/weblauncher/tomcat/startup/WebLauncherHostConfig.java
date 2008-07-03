package werkzeugkasten.weblauncher.tomcat.startup;

import java.io.File;

import org.apache.catalina.startup.HostConfig;

public class WebLauncherHostConfig extends HostConfig {

	public static final String KEY_CTX_LOC = "weblauncher.ctx.loc";
	public static final String KEY_CTX_NAME = "weblauncher.ctx.name";

	@Override
	protected void deployApps() {
		super.deployApps();
		String loc = System.getProperty(KEY_CTX_LOC);
		String name = System.getProperty(KEY_CTX_NAME);
		if (isEmpty(loc) == false && isEmpty(name) == false
				&& isServiced(name) == false) {
			File file = new File(loc);
			if (file.exists() && file.isFile()) {
				deployDescriptor(name, file, file.getName());
			}
		}
	}

	static final boolean isEmpty(String s) {
		return s == null || s.length() < 1;
	}
}
