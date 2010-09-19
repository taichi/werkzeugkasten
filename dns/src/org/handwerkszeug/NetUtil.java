package org.handwerkszeug;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtil {

	public static InetAddress getByAddress(long address) {
		byte[] a = new byte[] {
				//
				(byte) ((address >>> 24) & 0xFF),
				(byte) ((address >>> 16) & 0xFF),
				(byte) ((address >>> 8) & 0xFF),
				//
				(byte) (address & 0xFF) };
		try {
			return InetAddress.getByAddress(a);
		} catch (UnknownHostException e) {
			return null;
		}
	}
}
