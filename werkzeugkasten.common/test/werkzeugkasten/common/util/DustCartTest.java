package werkzeugkasten.common.util;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

public class DustCartTest {

	@Test
	public void testPickUp() throws Exception {
		URL[] urls = collectFiles();

		ClassLoader classLoader = new URLClassLoader(urls);
		DustCart cart = new DustCart();
		cart.pickUp(classLoader);
	}

	static final Pattern lib = Pattern.compile(".*\\.(jar|zip)",
			Pattern.CASE_INSENSITIVE);

	protected URL[] collectFiles() throws Exception {
		List<URL> result = new ArrayList<URL>();
		File testLib = new File("lib-test");
		File[] files = testLib.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return lib.matcher(name).matches();
			}
		});
		for (File f : files) {
			result.add(f.toURI().toURL());
		}
		return result.toArray(new URL[result.size()]);
	}

}
