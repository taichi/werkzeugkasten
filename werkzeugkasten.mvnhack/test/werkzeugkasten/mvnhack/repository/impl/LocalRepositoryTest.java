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
import werkzeugkasten.mvnhack.repository.Artifact;

public class LocalRepositoryTest {

	LocalRepository target;

	File root;
	File another;

	StAXArtifactBuilder builder;

	DefaultContext context;

	@BeforeClass
	public static void setUpClass() {
		UrlUtil.setDefaultUseCaches();
	}

	@Before
	public void setUp() throws Exception {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL url = cl.getResource("repository");
		File kid = new File(url.getPath());
		root = kid.getParentFile();
		another = new File(root, "another");
		builder = new StAXArtifactBuilder();
		context = new DefaultContext(new DefaultConfiguration());
		target = new LocalRepository(kid, builder);
	}

	@After
	public void tearDown() throws Exception {
		if (another.exists()) {
			FileUtil.delete(another);
		}
	}

	@Test
	public void testLoad() {
		Artifact a = target.load(context, "net.sourceforge.jexcelapi", "jxl",
				"2.6.6");
		assertNotNull(a);
		Set<URL> set = target.getLocation(a);
		assertNotNull(set);
		assertEquals(3, set.size());

		if (another.exists()) {
			FileUtil.delete(another);
		}
		LocalRepository lr = new LocalRepository(another, builder);
		lr.copyFrom(new DefaultContext(new DefaultConfiguration()), target, a);
		assertNotNull(lr.load(context, "net.sourceforge.jexcelapi", "jxl",
				"2.6.6"));
	}
}
