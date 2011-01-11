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

		Pattern v4PatternWithPort = Pattern.compile(withV4PortNumber(v4Pattern
				.pattern()));
		assertTrue(v4PatternWithPort, v4address);

		String[] v4addrWithPort = { "192.168.0.1:1", "127.0.0.1:80",
				"255.255.0.1:8080" };
		assertTrue(v4PatternWithPort, v4addrWithPort);

		String[] v4addrWithIlleagalPort = { "192.168.0.1:10000",
				"127.0.0.1:80801" };
		assertFalse(v4PatternWithPort, v4addrWithIlleagalPort);

		System.out.println("IPv4 Address with Port Number");
		System.out.println(v4PatternWithPort.pattern());
	}

	protected String withV4PortNumber(String pattern) {
		return pattern + "(:\\d{1,4})?";
	}

	@Test
	public void ipV6AddressMatcher() throws Exception {
		String v6Partial = "[0-9a-f]{1,4}";
		Pattern v6PartialPattern = Pattern.compile(v6Partial);
		String[] matchcase = { "2001", "0011", "db8", "bbbb" };
		assertTrue(v6PartialPattern, matchcase);

		String[] ignorecase = { "0g", "G", "aZF", "A", "00001", "AaAa" };
		assertFalse(v6PartialPattern, ignorecase);

		String v6PattenBase = "(" + v6Partial + ")(:(" + v6Partial + "))";
		Pattern v6Pattern = Pattern.compile(v6PattenBase + "{7}");
		String[] v6address = { "2001:db8:0:0:8:800:200c:417a",
				"ff02:0:0:0:0:1:ffff:ffff" };
		assertTrue(v6Pattern, v6address);

		Pattern v6withPort = Pattern.compile(withV6PortNumber(v6Pattern
				.pattern()));
		assertTrue(v6withPort, v6address);

		System.out.println("IPv6 Address with Port Number");
		System.out.println(v6withPort.pattern());

		String negativeLookAhead = "(?!([0-9a-f]{1,4})(::?([0-9a-f]{1,4})){8,})";

		String compressedV6Partial = "(" + v6PattenBase + "{0,5})?";
		Pattern compressedV6 = Pattern.compile(negativeLookAhead
				+ compressedV6Partial + "::" + compressedV6Partial);
		String[] compressedAddr = { "2001:db8:aaaa:bbbb:cccc:dddd::1",
				"2001:db8::1", "2001::1", "::1", "::", "2001:db8::",
				"2001::db8:aaaa:bbbb:cccc:dddd:eeee" };
		assertTrue(compressedV6, compressedAddr);

		System.out.println(compressedV6.pattern());

		Pattern compressedV6WithPort = Pattern
				.compile(withV6PortNumber(compressedV6.pattern()));
		String[] v6addrWithPort = { "[::]:80", "[2001:db8::1]:80",
				"2001:db8::1.80", "2001:db8::1 port 80", "2001:db8::1p80",
				"2001:db8::1#80" };
		assertTrue(compressedV6WithPort, v6addrWithPort);

		String[] v6addrWithIlleagalPort = { "[2001:db8::1]80",
				"2001:db8::1 80", "2001:db8::1port80", "2001:db8::1 prot 80",
				"2001:db8::1 p 80", "2001:db8::1 # 80" };
		assertFalse(compressedV6WithPort, v6addrWithIlleagalPort);

		System.out.println("compressed IPv6 Address with Port Number");
		System.out.println(compressedV6WithPort.pattern());

		String[] invalidCompressedAddr = { "2001:db8:aaaa:bbbb:cccc:dddd::1:03:ff:2b" };
		assertFalse(compressedV6, invalidCompressedAddr);

		String v4Partial = "25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d";
		String v4 = "(" + v4Partial + ")(\\.(" + v4Partial + ")){3}";
		Pattern v6WithV4 = Pattern.compile("((" + v6Partial + "):){6}" + v4);
		String[] v6WithV4Addr = { "0:0:0:0:0:0:13.1.68.3",
				"0:0:0:0:0:ffff:129.144.52.38" };
		assertTrue(v6WithV4, v6WithV4Addr);

		System.out.println("IPv6 Address with IPv4 Address");
		System.out.println(v6WithV4.pattern());

		Pattern compressedV6WithV4 = Pattern.compile(compressedV6Partial + "::"
				+ "((" + v6Partial + "):){0,5}" + v4);

		String[] compressedV6WithV4Addr = { "::13.1.68.3",
				"::ffff:129.144.52.38", "2001:db8::13.1.68.3" };
		assertTrue(compressedV6WithV4, compressedV6WithV4Addr);

		Pattern compressedV6WithV4andPort = Pattern
				.compile(withV6PortNumber(compressedV6WithV4.pattern()));

		String[] compressedV6WithV4AddrAndPort = { "[::13.1.68.3]:80",
				"::ffff:129.144.52.38.80", "2001:db8::13.1.68.3#80" };
		assertTrue(compressedV6WithV4andPort, compressedV6WithV4AddrAndPort);

		System.out
				.println("compressed IPv6 Address with IPv4 Address and Port Number");
		System.out.println(compressedV6WithV4andPort.pattern());
	}

	protected String withV6PortNumber(String pattern) {
		String port = "\\d{1,4}";
		return "(" + pattern + "((\\.|#|p| port )" + port + ")?)|\\[" + pattern
				+ "\\]:" + port;
	}

	/**
	 * 
	 * @throws Exception
	 * @see http://www.intermapper.com/ipv6validator
	 * @see http://download.dartware.com/thirdparty/test-ipv6-regex.pl
	 */
	@Test
	public void fromDartwareDotCom() throws Exception {
		String regex = "((([0-9a-f]{1,4}:){7}([0-9a-f]{1,4}|:))|(([0-9a-f]{1,4}:){6}(:[0-9a-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9a-f]{1,4}:){5}(((:[0-9a-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9a-f]{1,4}:){4}(((:[0-9a-f]{1,4}){1,3})|((:[0-9a-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9a-f]{1,4}:){3}(((:[0-9a-f]{1,4}){1,4})|((:[0-9a-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9a-f]{1,4}:){2}(((:[0-9a-f]{1,4}){1,5})|((:[0-9a-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9a-f]{1,4}:){1}(((:[0-9a-f]{1,4}){1,6})|((:[0-9a-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9a-f]{1,4}){1,7})|((:[0-9a-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?";
		// String regex =
		// "(((?=(?>.*?::)(?!.*::)))(::)?([0-9a-f]{1,4}::?){0,5}|([0-9a-f]{1,4}:){6})(\\2([0-9a-f]{1,4}(::?|$)){0,2}|((25[0-5]|(2[0-4]|1[0-9]|[1-9])?[0-9])(\\.|$)){4}|[0-9a-f]{1,4}:[0-9a-f]{1,4})(?<![^:]:)(?<!\\.)";
		Pattern p = Pattern.compile(regex);

		String[] v6address = { "2001:db8:0:0:8:800:200c:417a",
				"ff02:0:0:0:0:1:ffff:ffff" };
		assertTrue(p, v6address);

		String[] compressedAddr = { "2001:db8:aaaa:bbbb:cccc:dddd::1",
				"2001:db8::1", "2001::1", "::1", "::", "2001:db8::",
				"2001::db8:aaaa:bbbb:cccc:dddd:eeee" };
		assertTrue(p, compressedAddr);

		String[] compressedV6WithV4Addr = { "::13.1.68.3",
				"::ffff:129.144.52.38", "2001:db8::13.1.68.3" };
		assertTrue(p, compressedV6WithV4Addr);

		String[] invalidCompressedAddr = {
				"2001:db8:aaaa:bbbb:cccc:dddd::1:03:ff:2b", "2001:db8::aa::bb",
				"2001:db8::256.0.0.1" };
		assertFalse(p, invalidCompressedAddr);

		System.out.println("IPv6 Address with IPv4 Address from dartware.com");
		System.out.println(p.pattern());

		Pattern pwp = Pattern.compile(withV6PortNumber(regex));
		String[] v6addrWithPort = { "[::]:80", "[2001:db8::1]:80",
				"2001:db8::1.80", "2001:db8::1 port 80", "2001:db8::1p80",
				"2001:db8::1#80" };
		assertTrue(pwp, v6addrWithPort);

		String[] compressedV6WithV4AddrAndPort = { "[::13.1.68.3]:80",
				"::ffff:129.144.52.38.80", "2001:db8::13.1.68.3#80" };
		assertTrue(pwp, compressedV6WithV4AddrAndPort);

		System.out
				.println("compressed IPv6 Address with IPv4 Address and Port Number from dartware.com");
		System.out.println(pwp.pattern());

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
