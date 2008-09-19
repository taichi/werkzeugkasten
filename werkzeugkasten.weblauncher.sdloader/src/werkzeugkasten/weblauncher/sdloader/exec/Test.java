package werkzeugkasten.weblauncher.sdloader.exec;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {

	public static void main(String[] args) throws Exception {
		File sdloader = new File("D:/Temp/executable.war", "sdloader-jsp20.jar");
		String serverMain = "sdloader.BrowserOpen";
		Thread thd = Thread.currentThread();
		ClassLoader current = thd.getContextClassLoader();
		try {
			URLClassLoader cl = new URLClassLoader(new URL[] { sdloader.toURI()
					.toURL() }, ClassLoader.getSystemClassLoader());
			Class<?> mainClass = cl.loadClass(serverMain);
			thd.setContextClassLoader(cl);
			Method main = mainClass.getMethod("main",
					new Class[] { java.lang.String[].class });
			main.invoke(null, new Object[] { new String[] {} });
		} finally {
			thd.setContextClassLoader(current);
		}

	}
}
