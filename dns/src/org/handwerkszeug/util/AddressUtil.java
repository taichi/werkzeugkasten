package org.handwerkszeug.util;

import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @see <a href="http://tools.ietf.org/html/rfc4291">[RFC4291] IP Version 6
 *      Addressing Architecture</a>
 * @see <a href="http://tools.ietf.org/html/rfc5952">[RFC5952] A Recommendation
 *      for IPv6 Address Text Representation</a>
 * @see http://www.intermapper.com/ipv6validator
 * @see http://download.dartware.com/thirdparty/test-ipv6-regex.pl
 * @author taichi
 */
public class AddressUtil {

	/**
	 * @see <a href="http://tools.ietf.org/html/rfc952">[RFC952] DOD INTERNET
	 *      HOST TABLE SPECIFICATION</a>
	 */
	public static final Pattern hostname = Pattern
			.compile("([a-zA-Z][\\w-]*[\\w]*(\\.[a-zA-Z][\\w-]*[\\w]*)*)");

	public static final Pattern hostWithPort = withPortNumber(hostname);

	public static final String under65536 = "(6553[0-5]|6(55[012]|(5[0-4]|[0-4]\\d)\\d)\\d|[1-5]?\\d{1,4})";
	public static final Pattern v4Address = Pattern
			.compile("((25[0-5]|(2[0-4]|1\\d|[1-9]?)\\d)(\\.|\\b)){4}(?<!\\.)");
	public static final Pattern v4withPort = withPortNumber(v4Address);

	protected static Pattern withPortNumber(Pattern p) {
		return Pattern.compile("(" + p.pattern() + ")(:" + under65536 + ")?");
	}

	protected static final String internal_v6address = "((((?=(?>.*?::)(?!.*::)))(::)?([0-9a-f]{1,4}::?){0,5}|([0-9a-f]{1,4}:){6})(((25[0-5]|(2[0-4]|1[0-9]|[1-9])?[0-9])(\\.|\\b)){4}|\\3([0-9a-f]{1,4}(::?|\\b)){0,2}|[0-9a-f]{1,4}:[0-9a-f]{1,4})(?<![^:]:)(?<!\\.))";
	public static final Pattern v6Address = Pattern.compile(internal_v6address
			+ "$");
	public static final Pattern v6withSuffixPort = Pattern
			.compile(internal_v6address + "(?:(#|\\.)" + under65536 + ")?$");

	public static final Pattern v6withBracketPort = Pattern.compile("\\["
			+ internal_v6address + "\\](:" + under65536 + ")?$"); // 1 15

	public static InetSocketAddress toSocketAddress(String addressWithPort,
			int defaultPort) {
		// TODO
		return null;
	}

	public static InetSocketAddress fromHostname(String host, int defaultPort) {
		return toSocketAddress(host, defaultPort, hostWithPort, 1, 5);
	}

	public static InetSocketAddress fromV4(String addr, int defaultPort) {
		return toSocketAddress(addr, defaultPort, v4withPort, 1, 7);
	}

	public static InetSocketAddress fromV6(String addr, int defaultPort) {
		InetSocketAddress result = toSocketAddress(addr, defaultPort,
				v6withSuffixPort, 1, 15);
		if (result == null) {
			result = toSocketAddress(addr, defaultPort, v6withBracketPort, 1,
					15);
		}
		return result;
	}

	protected static InetSocketAddress toSocketAddress(String addressWithPort,
			int defaultPort, Pattern p, int HOST, int PORT) {
		Matcher m = p.matcher(addressWithPort);
		if (m.matches() && m.reset().find()) {
			int port = toInt(m.group(PORT), defaultPort);
			return new InetSocketAddress(m.group(HOST), port);
		}
		return null;
	}

	protected static int toInt(String s, int defaultValue) {
		int result = defaultValue;
		try {
			if ((s != null) && (s.isEmpty() == false)) {
				result = Integer.parseInt(s);
			}
		} catch (NumberFormatException e) {
		}
		return result;
	}
}
