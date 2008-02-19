package werkzeugkasten.mvnhack.repository.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import werkzeugkasten.common.util.UrlUtil;

public class DefaultContextTest {

	DefaultContext target;

	File destDir;

	@BeforeClass
	public static void setUpClass() {
		UrlUtil.setDefaultUseCaches();
	}

	@Before
	public void setUp() throws Exception {
		ArtifactBuilder builder = new ArtifactBuilder();

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL url = cl.getResource("repository");
		File repository = new File(url.getPath());
		DefaultConfiguration config = new DefaultConfiguration();
		config.addRepository(new LocalRepository(repository, builder));
		File repo = new File(repository.getParentFile(), "repo");
		config.addRepository(new LocalRepository(repo, builder));

		url = cl.getResource(".");
		destDir = new File(url.getPath(), "dest");
		destDir.deleteOnExit();
		FlatDestination flat = new FlatDestination(destDir);
		config.addDestination(flat);

		this.target = new DefaultContext(config);
	}

	@Test
	public void testResolve() {
		this.target.resolve(null, "commons-httpclient", "2.0.2");

		File[] files = destDir.listFiles();
		assertEquals(2, files.length);

	}

}
