package werkzeugkasten.mvnhack.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Artifact;

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
		StAXArtifactBuilder builder = new StAXArtifactBuilder();
		Artifact a = builder.build(new DefaultContext(
				new DefaultConfiguration()), in);
		assertNotNull(a);
		assertEquals("groupId", a.getGroupId());
		assertEquals("artifactId", a.getArtifactId());
		assertEquals("1.0.0", a.getVersion());

		Set<Artifact> set = a.getDependencies();
		assertEquals(2, set.size());
		Artifact[] ary = set.toArray(new Artifact[set.size()]);
		assertEquals("dependencyGroupId", ary[0].getGroupId());
		assertEquals("dependencyArtifactId", ary[0].getArtifactId());
		assertEquals("2.0.0", ary[0].getVersion());

		assertEquals("dependencyGroupId2", ary[1].getGroupId());
		assertEquals("dependencyArtifactId2", ary[1].getArtifactId());
		assertEquals("3.0.0", ary[1].getVersion());

	}

	@Test
	public void testArtifactBuild() throws Exception {
		DefaultArtifact a = new DefaultArtifact();
		StAXArtifactBuilder builder = new StAXArtifactBuilder();
		Map<String, StAXArtifactBuilder.Handler> m = builder
				.createArtifactParseHandlers(a);
		builder.parse(builder.createStreamParser(in), m, "project");

		assertEquals("groupId", a.getGroupId());
		assertEquals("artifactId", a.getArtifactId());
		assertEquals("1.0.0", a.getVersion());
	}

	@Test
	public void testReconcile() throws Exception {
		DefaultArtifact a = new DefaultArtifact();
		a.setGroupId("groupId");
		a.setArtifactId("artifactId");
		a.setVersion("1.0.0");
		Map<String, String> m = new HashMap<String, String>();
		StAXArtifactBuilder builder = new StAXArtifactBuilder();
		builder.reconcile(a, a, m);

		assertEquals("groupId", a.getGroupId());
		assertEquals("artifactId", a.getArtifactId());
		assertEquals("1.0.0", a.getVersion());
	}

	@Test
	public void testSkip() throws Exception {
		final Map<String, StAXArtifactBuilder.Handler> targets = new HashMap<String, StAXArtifactBuilder.Handler>();

		final int[] groupIdTimes = { 0 };
		targets.put("groupId", new StAXArtifactBuilder.Handler() {
			@Override
			public String getTagName() {
				return "groupId";
			}

			@Override
			public void handle(XMLStreamReader reader)
					throws XMLStreamException {
				assertTrue(targets.containsKey(reader.getLocalName()));
				assertEquals("groupId", reader.getElementText());
				groupIdTimes[0]++;
			}
		});
		final int[] dependenciesTimes = { 0 };
		targets.put("dependencies", new StAXArtifactBuilder.Handler() {
			@Override
			public String getTagName() {
				return "dependencies";
			}

			@Override
			public void handle(XMLStreamReader reader) {
				dependenciesTimes[0]++;
			}
		});

		StAXArtifactBuilder builder = new StAXArtifactBuilder();
		builder.parse(builder.createStreamParser(in), targets, "project");

		assertEquals(1, groupIdTimes[0]);
		assertEquals(1, dependenciesTimes[0]);
	}

}
