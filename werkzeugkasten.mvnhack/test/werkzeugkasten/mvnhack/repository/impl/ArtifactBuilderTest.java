package werkzeugkasten.mvnhack.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.common.util.XMLEventParser;
import werkzeugkasten.common.util.XMLEventParser.DefaultHandler;
import werkzeugkasten.common.util.XMLEventParser.Handler;
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
		Streams.close(in);
	}

	@Test
	public void testBuild() {
		assertNotNull(in);
		StAXArtifactBuilder builder = new StAXArtifactBuilder();
		Artifact a = builder.build(new DefaultContext(
				new PropertiesConfiguration()), in);
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
		XMLEventParser parser = new XMLEventParser(in);
		builder.addArtifactParseHandlers(parser, a);
		parser.add(new DefaultHandler("project"));
		parser.parse();

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
		XMLEventParser parser = new XMLEventParser(in);
		parser.add(new DefaultHandler("project"));

		final int[] groupIdTimes = { 0 };
		parser.add(new Handler() {
			@Override
			public String getTagName() {
				return "groupId";
			}

			@Override
			public void handle(XMLStreamReader reader)
					throws XMLStreamException {
				assertEquals("groupId", reader.getElementText());
				groupIdTimes[0]++;
			}
		});
		final int[] dependenciesTimes = { 0 };
		parser.add(new Handler() {
			@Override
			public String getTagName() {
				return "dependencies";
			}

			@Override
			public void handle(XMLStreamReader reader) {
				dependenciesTimes[0]++;
			}
		});

		parser.parse();

		assertEquals(1, groupIdTimes[0]);
		assertEquals(1, dependenciesTimes[0]);
	}

}
