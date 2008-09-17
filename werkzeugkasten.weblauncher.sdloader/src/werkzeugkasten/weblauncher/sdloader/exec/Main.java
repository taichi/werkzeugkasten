package werkzeugkasten.weblauncher.sdloader.exec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class Main {

	public static void main(String[] args) throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		for (Enumeration<URL> e = loader.getResources("META-INF/MANIFEST.MF"); e
				.hasMoreElements();) {
			URL u = e.nextElement();
			URLConnection connection = u.openConnection();
			connection.setDefaultUseCaches(false);
			if (connection instanceof JarURLConnection) {
				JarURLConnection jarcon = (JarURLConnection) connection;
				Manifest m = read(jarcon.getInputStream());
				Attributes attrs = m.getMainAttributes();
				String serverMain = attrs.getValue("Server-Main");
				String lib = attrs.getValue("Server-Lib");
				if (lib != null && 0 < lib.length() && serverMain != null
						&& 0 < serverMain.length()) {
					File me = new File(jarcon.getJarFile().getName());
					File tmpJar = copyfiles(loader, lib, me);
					executeServer(serverMain, tmpJar);
					break;
				}
			}
		}
	}

	private static File copyfiles(ClassLoader loader, String lib, File me)
			throws IOException {
		URL jar = loader.getResource(lib);
		final File tmpDir = new File(System.getProperty("java.io.tmpdir"),
				"executable.war");
		if (tmpDir.exists()) {
			delete(tmpDir); // delete previus extracted files
		}
		tmpDir.mkdirs();
		tmpDir.deleteOnExit();
		File tmpMe = File.createTempFile(me.getName(), null, new File(tmpDir,
				"webapps"));
		tmpMe.deleteOnExit();
		File tmpJar = File.createTempFile(lib, null, tmpDir);
		tmpJar.deleteOnExit();

		copy(jar.openStream(), new FileOutputStream(tmpJar));
		copy(new FileInputStream(me), new FileOutputStream(tmpMe));

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					delete(tmpDir);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return tmpJar;
	}

	private static void executeServer(String serverMain, File tmpJar)
			throws Exception {
		ClassLoader cl = new URLClassLoader(
				new URL[] { tmpJar.toURI().toURL() });
		Class<?> mainClass = cl.loadClass(serverMain);
		Method main = mainClass.getMethod("main",
				new Class[] { java.lang.String[].class });
		main.invoke(null);
	}

	private static Manifest read(InputStream in) throws IOException {
		try {
			return new Manifest(in);
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	private static void copy(InputStream in, OutputStream out)
			throws IOException {
		byte buf[] = new byte[8192];
		int len;
		try {
			while (0 < (len = in.read(buf))) {
				out.write(buf, 0, len);
			}
		} finally {
			in.close();
			out.close();
		}
	}

	private static void delete(File file) throws IOException {
		if (file.isDirectory()) {
			File files[] = file.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++)
					delete(files[i]);

			}
		}
		file.delete();
	}
}
