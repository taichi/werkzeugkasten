package werkzeugkasten.mvnhack.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Repository;

public class YamlConfigurationTest {

	InputStream in;

	static String currentProxy;
	static String currentPort;
	static String currentNonProxy;

	@BeforeClass
	public static void setUpClass() {
		UrlUtil.setDefaultUseCaches();
		currentProxy = System.getProperty("http.proxyHost");
		currentPort = System.getProperty("http.proxyPort");
		currentNonProxy = System.getProperty(Constants.PROP_NONPROXY);
		System.setProperty(Constants.PROP_PROXY, "");
		System.setProperty(Constants.PROP_NONPROXY, "");
	}

	@AfterClass
	public static void tearDownClass() {
		if (currentProxy != null) {
			System.setProperty("http.proxyHost", currentProxy);
		}
		if (currentPort != null) {
			System.setProperty("http.proxyPort", currentPort);
		}
		if (currentNonProxy != null) {
			System.setProperty(Constants.PROP_NONPROXY, currentNonProxy);
		}
	}

	@Before
	public void setUp() throws Exception {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		String yml = getClass().getName().replace('.', '/') + ".yml";
		in = cl.getResourceAsStream(yml);
	}

	@After
	public void tearDown() {
		Streams.close(in);
	}

	@Test
	public void testLoad() throws Exception {
		Yaml yaml = new Yaml();
		Node node = yaml.compose(new InputStreamReader(in));
		assertEquals(NodeId.mapping, node.getNodeId());
		MappingNode mn = (MappingNode) node;

		YamlConfiguration target = new YamlConfiguration();
		target.load(mn);

		assertEquals("proxy.example.com", System.getProperty("http.proxyHost"));
		assertEquals("8080", System.getProperty("http.proxyPort"));
		assertEquals("localhost", System.getProperty(Constants.PROP_NONPROXY));

		Set<String> urls = new HashSet<String>();
		for (Repository r : target.getRepositories()) {
			if (r instanceof RemoteRepository) {
				RemoteRepository rr = (RemoteRepository) r;
				urls.add(rr.baseUrl);
			}
		}

		assertTrue(urls.contains("http://repository.codehaus.org/"));
		assertTrue(urls
				.contains("https://repository.jboss.org/nexus/content/repositories/releases/"));
		assertTrue(urls.contains("http://repos.example.com/"));

	}
}
