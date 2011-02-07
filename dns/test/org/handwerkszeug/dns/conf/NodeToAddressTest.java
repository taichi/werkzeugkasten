package org.handwerkszeug.dns.conf;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
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

	protected void testScalarToAddress(SocketAddress act, String data) {
		Node node = this.yaml.compose(new StringReader(data));
		ScalarToAddress target = new ScalarToAddress();
		List<SocketAddress> context = new ArrayList<SocketAddress>();
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

	protected void testMappingToAddress(SocketAddress act, String data) {
		Node node = this.yaml.compose(new StringReader(data));
		MappingToAddress target = new MappingToAddress();
		List<SocketAddress> context = new ArrayList<SocketAddress>();
		target.handle(node, context);
		assertEquals(act, context.get(0));
	}

	@Test
	public void testSequenceToAddress() {
		String data = "- 127.0.0.1:80\n- 192.168.0.1";
		InetSocketAddress act = new InetSocketAddress("127.0.0.1", 80);
		Node node = this.yaml.compose(new StringReader(data));
		NodeToAddress target = new NodeToAddress();
		List<SocketAddress> context = new ArrayList<SocketAddress>();
		target.handle(node, context);
		assertEquals(2, context.size());
		assertEquals(act, context.get(0));
	}
}
