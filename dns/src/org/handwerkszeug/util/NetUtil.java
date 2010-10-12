package org.handwerkszeug.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetUtil {

	static final Logger LOG = LoggerFactory.getLogger(NetUtil.class);

	public static InetAddress getByAddress(long address) {
		byte[] a = new byte[] { (byte) ((address >>> 24) & 0xFF),
				(byte) ((address >>> 16) & 0xFF),
				(byte) ((address >>> 8) & 0xFF), (byte) (address & 0xFF) };
		try {
			return InetAddress.getByAddress(a);
		} catch (UnknownHostException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return null;
		}
	}
}
