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

	ArtifactBuilder builder;

	RemoteRepository target;

	FlatDestination flat;

	File localRoot;

	@BeforeClass
	public static void setUpClass() {
		UrlUtil.setDefaultUseCaches();
	}

	@Before
	public void setUp() throws Exception {
		builder = new ArtifactBuilder();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL url = cl.getResource(".");
		localRoot = new File(url.getPath(), "local");
		if (localRoot.exists()) {
			FileUtil.delete(localRoot);
		}
		flat = new FlatDestination(localRoot);
		target = new RemoteRepository(Constants.CENTRAL_REPOSITORY, builder);
	}

	@After
	public void tearDown() throws Exception {
		FileUtil.delete(localRoot);
	}

	@Test
	public void testLoad() {
		Artifact a = target.load("net.sourceforge.jexcelapi", "jxl", "2.6.3");
		assertNotNull(a);
		Set<URL> set = target.getLocation(a);
		assertNotNull(set);
		assertEquals(3, set.size());

		flat
				.copyFrom(new DefaultContext(new DefaultConfiguration()),
						target, a);
		assertEquals(1, localRoot.list().length);
	}

}
