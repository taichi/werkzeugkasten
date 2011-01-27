package org.handwerkszeug.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.InetSocketAddress;

import org.junit.Before;
import org.junit.Test;

public class AddressUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToV4SocketAddress() {
		String[] v4address = { "192.168.0.1", "127.0.0.1", "255.255.0.1" };
		for (String s : v4address) {
			int port = 53;
			InetSocketAddress sa = AddressUtil.toV4SocketAddress(s, port);
			assertNotNull(sa);
			assertEquals(s, sa.getAddress().getHostAddress());
			assertEquals(port, sa.getPort());
		}
		String[] v4addrWithPort = { "192.168.0.1:1", "127.0.0.1:80",
				"255.255.0.1:8080" };
		int[] ports = { 1, 80, 8080 };
		for (int i = 0, length = ports.length; i < length; i++) {
			String s = v4addrWithPort[i];
			InetSocketAddress sa = AddressUtil.toV4SocketAddress(s, 22);
			assertNotNull(sa);
			assertEquals(s.substring(0, s.lastIndexOf(':')), sa.getAddress()
					.getHostAddress());
			assertEquals(ports[i], sa.getPort());
		}
	}

}
