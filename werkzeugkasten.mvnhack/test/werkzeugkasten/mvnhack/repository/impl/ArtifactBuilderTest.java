package werkzeugkasten.mvnhack.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Dependency;

public class ArtifactBuilderTest {

	InputStream in;

	@BeforeClass
	public static void setUpClass() {
		UrlUtil.setDefaultUseCaches();
	}

	@Before
	public void setUp() throws Exception {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		String pom = getClass().getName().replace('.', '/') + ".pom";
		in = cl.getResourceAsStream(pom);
	}

	@After
	public void tearDown() {
		StreamUtil.close(in);
	}

	@Test
	public void testBuild() {
		assertNotNull(in);
		ArtifactBuilder ab = new ArtifactBuilder();
		Artifact a = ab.build(new DefaultContext(new DefaultConfiguration()),
				in);
		assertNotNull(a);
		assertEquals("groupId", a.getGroupId());
		assertEquals("artifactId", a.getArtifactId());
		assertEquals("1.0.0", a.getVersion());

		Set<Dependency> set = a.getDependencies();
		assertEquals(2, set.size());
		Dependency[] ary = set.toArray(new Dependency[set.size()]);
		assertEquals("dependencyGroupId", ary[0].getGroupId());
		assertEquals("dependencyArtifactId", ary[0].getArtifactId());
		assertEquals("2.0.0", ary[0].getVersion());

		assertEquals("dependencyGroupId2", ary[1].getGroupId());
		assertEquals("dependencyArtifactId2", ary[1].getArtifactId());
		assertEquals("3.0.0", ary[1].getVersion());

	}

}
