package org.handwerkszeug;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.InetAddress;

import org.junit.Test;

public class NetUtilTest {

	@Test
	public void testGetByAddress() {
		String expected = "192.168.10.1";
		// 11000000 10101000 00001010 00000001
		long address = 3232238081L;
		InetAddress actual = NetUtil.getByAddress(address);
		assertNotNull(actual);
		assertEquals(expected, actual.getHostAddress());

		// 11 11000000 10101000 00001010 00000001
		address = 16117139969L;
		actual = NetUtil.getByAddress(address);

		assertEquals(expected, actual.getHostAddress());
	}

}
