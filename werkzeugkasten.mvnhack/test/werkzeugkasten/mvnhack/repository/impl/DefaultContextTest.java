package werkzeugkasten.mvnhack.repository.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import werkzeugkasten.common.util.FileUtil;
import werkzeugkasten.common.util.UrlUtil;

public class DefaultContextTest {

	DefaultContext target;

	File destDir;

	File destRepo;

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
		if (destDir.exists()) {
			FileUtil.delete(destDir);
		}
		FlatDestination flat = new FlatDestination(destDir);
		config.addDestination(flat);

		destRepo = new File(url.getPath(), "destrepo");
		if (destRepo.exists()) {
			FileUtil.delete(destRepo);
		}
		LocalRepository lrdest = new LocalRepository(destRepo, builder);
		config.addDestination(lrdest);

		this.target = new DefaultContext(config);
	}

	@After
	public void tearDown() throws Exception {
		if (destDir.exists()) {
			FileUtil.delete(destDir);
		}
		if (destRepo.exists()) {
			FileUtil.delete(destRepo);
		}
	}

	@Test
	public void testResolve() {
		target.resolve("commons-httpclient", "commons-httpclient", "2.0.2");

		File[] files = destDir.listFiles();
		assertEquals(2, files.length);

		File http = new File(destRepo,
				"commons-httpclient/commons-httpclient/2.0.2");
		assertEquals(true, http.exists());
		assertEquals(2, http.list().length);

		File logging = new File(destRepo,
				"commons-logging/commons-logging/1.0.3");
		assertEquals(true, logging.exists());
		File jar = new File(logging, "commons-logging-1.0.3.jar");
		assertEquals(true, jar.exists());
	}

}
