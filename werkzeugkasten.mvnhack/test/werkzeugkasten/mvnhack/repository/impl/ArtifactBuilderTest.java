package werkzeugkasten.mvnhack.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Set;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
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
	public void testA() throws Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,
				Boolean.FALSE);
		BufferedInputStream stream = new BufferedInputStream(in);
		XMLStreamReader reader = factory.createXMLStreamReader(stream);
		reader = factory.createFilteredReader(reader, new StreamFilter() {
			@Override
			public boolean accept(XMLStreamReader reader) {
				return reader.isStartElement() || reader.isEndElement();
			}
		});
		for (; reader.hasNext(); reader.next()) {
			String localname = reader.getLocalName();
			if ("developers".equals(localname)) {
				for (; reader.hasNext();) {
					int event = reader.next();
					if (XMLStreamConstants.END_ELEMENT == event) {
						String end = reader.getLocalName();
						if (localname.equals(end)) {
							break;
						}
					}
				}
			}
		}
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

}
