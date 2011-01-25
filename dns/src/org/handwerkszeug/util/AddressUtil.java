package org.handwerkszeug.util;

import java.net.InetSocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressUtil {

	public static String v4Partial = "25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d";
	public static String v4Address = "((" + v4Partial + ")(\\.(" + v4Partial
			+ ")){3})";
	public static String v4addrWithPort = v4Address + "(:(\\d{1,5}))?";
	public static Pattern v4Pattern = Pattern.compile(v4addrWithPort);

	public static InetSocketAddress toV4SocketAddress(String addressWithPort,
			int defaultPort) {
		int HOST = 1;
		int PORT = 6;
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
			result = Integer.parseInt(s);
		} catch (NumberFormatException e) {
		}
		return result;
	}
}
