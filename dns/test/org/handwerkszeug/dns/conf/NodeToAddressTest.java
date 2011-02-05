package org.handwerkszeug.dns.conf;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.dns.conf.NodeToAddress.MappingToAddress;
import org.handwerkszeug.dns.conf.NodeToAddress.ScalarToAddress;
import org.junit.Before;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Node;

public class NodeToAddressTest {

	Yaml yaml;

	@Before
	public void setUp() throws Exception {
		this.yaml = new Yaml();
	}

	@Test
	public void testScalarToAddress() {
		testScalarToAddress(new InetSocketAddress("127.0.0.1", 8080),
				"127.0.0.1:8080");
		testScalarToAddress(new InetSocketAddress("127.0.0.1", 53), "127.0.0.1");
	}

	protected void testScalarToAddress(InetSocketAddress act, String data) {
		Node node = this.yaml.compose(new StringReader(data));
		ScalarToAddress target = new ScalarToAddress();
		List<InetSocketAddress> context = new ArrayList<InetSocketAddress>();
		target.handle(node, context);
		assertEquals(act, context.get(0));
	}

	@Test
	public void testMappingToAddress() {
		testMappingToAddress(new InetSocketAddress("127.0.0.1", 53),
				"address : 127.0.0.1\nport : 53");
		testMappingToAddress(new InetSocketAddress("127.0.0.1", 8080),
				"127.0.0.1 : 8080");
	}

	protected void testMappingToAddress(InetSocketAddress act, String data) {
		Node node = this.yaml.compose(new StringReader(data));
		MappingToAddress target = new MappingToAddress();
		List<InetSocketAddress> context = new ArrayList<InetSocketAddress>();
		target.handle(node, context);
		assertEquals(act, context.get(0));
	}
}
