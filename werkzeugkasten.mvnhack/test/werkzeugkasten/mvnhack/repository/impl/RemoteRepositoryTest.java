package werkzeugkasten.mvnhack.repository.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import werkzeugkasten.common.util.FileUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;

public class RemoteRepositoryTest {

	DefaultContext context;

	RemoteRepository target;

	File localRoot;

	@BeforeClass
	public static void setUpClass() {
		UrlUtil.setDefaultUseCaches();
	}

	@Before
	public void setUp() throws Exception {
		ArtifactBuilder builder = new ArtifactBuilder();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL url = cl.getResource(".");
		localRoot = new File(url.getPath(), "local");
		if (localRoot.exists()) {
			FileUtil.delete(localRoot);
		}
		FlatDestination flat = new FlatDestination(localRoot);
		target = new RemoteRepository(Constants.CENTRAL_REPOSITORY, builder);
		DefaultConfiguration conf = new DefaultConfiguration();
		conf.addRepository(target);
		conf.addDestination(flat);
		context = new DefaultContext(conf);
	}

	@After
	public void tearDown() throws Exception {
		FileUtil.delete(localRoot);
	}

	@Test
	public void testLoad() {
		Artifact a = target.load(context, "org.slf4j", "slf4j-simple", "1.4.3");
		assertNotNull(a);
		Set<URL> set = target.getLocation(a);
		assertNotNull(set);
		assertEquals(3, set.size());

		assertEquals(2, localRoot.list().length);
	}

}
