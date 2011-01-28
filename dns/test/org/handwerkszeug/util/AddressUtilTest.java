package org.handwerkszeug.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.net.InetSocketAddress;

import org.junit.Before;
import org.junit.Test;

public class AddressUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFromV4() {
		String[] v4address = { "192.168.0.1", "127.0.0.1", "255.255.0.1" };
		for (String s : v4address) {
			int port = 53;
			InetSocketAddress sa = AddressUtil.fromV4(s, port);
			assertNotNull(sa);
			assertEquals(s, sa.getAddress().getHostAddress());
			assertEquals(port, sa.getPort());
		}
		String[] v4addrWithPort = { "192.168.0.1:1", "127.0.0.1:80",
				"255.255.0.1:8080" };
		int[] ports = { 1, 80, 8080 };
		for (int i = 0, length = ports.length; i < length; i++) {
			String s = v4addrWithPort[i];
			InetSocketAddress sa = AddressUtil.fromV4(s, 22);
			assertNotNull(sa);
			assertEquals(s.substring(0, s.lastIndexOf(':')), sa.getAddress()
					.getHostAddress());
			assertEquals(ports[i], sa.getPort());
		}

		String[] v4addrWithIlleagalPort = { "192.168.0.1:65536",
				"127.0.0.1:80801" };
		for (String s : v4addrWithIlleagalPort) {
			assertNull(AddressUtil.fromV4(s, 21));
		}
	}

	@Test
	public void testFromHostName() {
		int port = 22;
		InetSocketAddress sa = AddressUtil.fromHostname("localhost", port);
		assertNotNull(sa);
		assertEquals("127.0.0.1", sa.getAddress().getHostAddress());
		assertEquals(port, sa.getPort());

		sa = AddressUtil.fromHostname("localhost:8080", 31);
		assertNotNull(sa);
		assertEquals("127.0.0.1", sa.getAddress().getHostAddress());
		assertEquals(8080, sa.getPort());

		assertNull(AddressUtil.fromHostname("localhost:65536", 21));
	}

	@Test
	public void testFromV6() {
		String[] v6address = { "2001:db8:0:0:8:800:200c:417a",
				"ff02:0:0:0:0:1:ffff:ffff", "2001:db8:aaaa:bbbb:cccc:dddd::1",
				"2001:db8::1", "2001::1", "::1", "::", "2001:db8::",
				"2001::db8:aaaa:bbbb:cccc:dddd:eeee", "::1:2:3:4" };
		for (String s : v6address) {
			int port = 53;
			InetSocketAddress sa = AddressUtil.fromV6(s, port);
			assertNotNull(s, sa);
			assertEquals(port, sa.getPort());
		}
		String[] invalidCompressedAddr = {
				"2001:db8:aaaa:bbbb:cccc:dddd::1:03:ff:2b", "2001:db8::aa::bb",
				"2001:db8::256.0.0.1" };
		for (String s : invalidCompressedAddr) {
			InetSocketAddress sa = AddressUtil.fromV6(s, 1);
			assertNull(s, sa);
		}
		String[] v6addrWithPort = { "[::]:81", "[2001:db8::1]:82",
				"2001:db8::1:1#22", "2001:db8:1:2::1.80", "2001:db8::1:2:3#83" };
		String[] addrExp = { "0:0:0:0:0:0:0:0", "2001:db8:0:0:0:0:0:1",
				"2001:db8:0:0:0:0:1:1", "2001:db8:1:2:0:0:0:1",
				"2001:db8:0:0:0:1:2:3" };
		int[] ports = { 81, 82, 22, 80, 83 };
		for (int i = 0, length = addrExp.length; i < length; i++) {
			String s = v6addrWithPort[i];
			String ae = addrExp[i];
			int p = ports[i];
			InetSocketAddress sa = AddressUtil.fromV6(s, 30);
			assertNotNull(s, sa);
			assertEquals(ae, sa.getAddress().getHostAddress());
			assertEquals(p, sa.getPort());
		}
	}
}
