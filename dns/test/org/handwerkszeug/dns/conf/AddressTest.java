package org.handwerkszeug.dns.conf;

import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author taichi
 * @see <a href="http://tools.ietf.org/html/rfc4291">IP Version 6 Addressing
 *      Architecture</a>
 * @see <a href="http://tools.ietf.org/html/rfc5952">A Recommendation for IPv6
 *      Address Text Representation</a>
 * @see<a 
 *        href="http://www.docjar.com/docs/api/sun/net/util/IPAddressUtil.html">sun
 *        .net.util: public class: IPAddressUtil</a>
 */
public class AddressTest {

	@Test
	public void ipV4AddressMatcher() throws Exception {
		String v4Partial = "25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d";
		Pattern v4PartialPattern = Pattern.compile(v4Partial);
		String[] matchcase = { "255", "209", "128", "00", "1" };
		assertTrue(v4PartialPattern, matchcase);

		String[] ignorecase = { "256", "301", "1000" };
		assertFalse(v4PartialPattern, ignorecase);

		Pattern v4Pattern = Pattern.compile("(" + v4Partial + ")(\\.("
				+ v4Partial + ")){3}");

		String[] v4address = { "192.168.0.1", "127.0.0.1", "255.255.0.1" };
		assertTrue(v4Pattern, v4address);
	}

	@Test
	public void ipV6AddressMatcher() throws Exception {
		String v6Partial = "[0-9a-fA-F]{1,4}";
		Pattern v6PartialPattern = Pattern.compile(v6Partial);
		String[] matchcase = { "2001", "0011", "A", "db8", "bbbb", "AaAa" };
		assertTrue(v6PartialPattern, matchcase);

		String[] ignorecase = { "0g", "G", "aZF", "00001" };
		assertFalse(v6PartialPattern, ignorecase);

		String v6PattenBase = "(" + v6Partial + ")(:(" + v6Partial + "))";
		Pattern v6Pattern = Pattern.compile(v6PattenBase + "{7}");
		String[] v6address = { "2001:DB8:0:0:8:800:200C:417A",
				"FF02:0:0:0:0:1:FFFF:FFFF" };
		assertTrue(v6Pattern, v6address);

		String compressedV6Partial = "(" + v6PattenBase + "{0,5})";
		Pattern compressedV6 = Pattern.compile("(" + compressedV6Partial
				+ "?)::" + compressedV6Partial);
		String[] compressedAddr = { "2001:db8:aaaa:bbbb:cccc:dddd::1",
				"2001:db8::1", "2001::1", "::1",
				"2001::db8:aaaa:bbbb:cccc:DDDD:eeEE" };
		assertTrue(compressedV6, compressedAddr);

		// invalid compressed address but test is passed.
		// any other compressed address test has same problem.
		String[] invalidAddr = { "2001:db8:aaaa:bbbb:cccc:dddd::1:03:ff:2b" };
		assertTrue(compressedV6, invalidAddr);

		String v4Partial = "25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d";
		String v4 = "(" + v4Partial + ")(\\.(" + v4Partial + ")){3}";
		Pattern v6WithV4 = Pattern.compile("((" + v6Partial + "):){6}" + v4);
		String[] v6WithV4Addr = { "0:0:0:0:0:0:13.1.68.3",
				"0:0:0:0:0:FFFF:129.144.52.38" };
		assertTrue(v6WithV4, v6WithV4Addr);

		Pattern compressedV6WithV4 = Pattern.compile("(" + compressedV6Partial
				+ "?)::" + "((" + v6Partial + "):){0,5}" + v4);

		String[] compressedV6WithV4Addr = { "::13.1.68.3",
				"::FFFF:129.144.52.38", "2001:db8::13.1.68.3" };
		assertTrue(compressedV6WithV4, compressedV6WithV4Addr);
	}

	protected void assertTrue(Pattern test, String[] cases) {
		for (String s : cases) {
			Assert.assertTrue(s, test.matcher(s).matches());
		}
	}

	protected void assertFalse(Pattern test, String[] cases) {
		for (String s : cases) {
			Assert.assertFalse(s, test.matcher(s).matches());
		}
	}
}
