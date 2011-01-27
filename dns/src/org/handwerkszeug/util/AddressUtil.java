package org.handwerkszeug.util;

import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressUtil {

	public static String under65536 = "(6553[0-5]|6(55[012]|(5[0-4]|[0-4]\\d)\\d)\\d|[1-5]?\\d{1,4})";
	public static String v4Address = "((25[0-5]|(2[0-4]|1\\d|[1-9]?)\\d)(\\.|\\b)){4}(?<!\\.)";
	public static String v4addrWithPort = "(" + v4Address + ")(:(" + under65536
			+ "))?";
	public static Pattern v4Pattern = Pattern.compile(v4addrWithPort);

	public static InetSocketAddress toSocketAddress(String addressWithPort,
			int defaultPort) {
		return null;
	}

	public static InetSocketAddress toV4SocketAddress(String addressWithPort,
			int defaultPort) {
		int HOST = 1;
		int PORT = 7;
		Matcher m = v4Pattern.matcher(addressWithPort);
		if (m.matches() && m.reset().find()) {
			int port = toInt(m.group(PORT), defaultPort);
			return new InetSocketAddress(m.group(HOST), port);
		}
		return null;
	}

	public static InetSocketAddress toV6SocketAddress(String addressWithPort,
			int defaultPort) {
		// TODO unimplemented...
		throw new UnsupportedOperationException();
	}

	protected static int toInt(String s, int defaultValue) {
		int result = defaultValue;
		try {
			if (s != null && s.isEmpty() == false) {
				result = Integer.parseInt(s);
			}
		} catch (NumberFormatException e) {
		}
		return result;
	}
}
