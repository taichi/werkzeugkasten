package org.handwerkszeug.dns.record;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.RRType;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * <a href="http://datatracker.ietf.org/doc/rfc3596/">RFC3596</a>
 * 
 * @author taichi
 * 
 */
public class AAAARecord extends AbstractRecord {

	/**
	 * A 128 bit IPv6 address is encoded in the data portion of an AAAA resource
	 * record in network byte order (high-order byte first).
	 */
	protected byte[] address;

	public AAAARecord() {
		super(RRType.AAAA);
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		byte[] newone = new byte[16];
		buffer.readBytes(newone);
		this.address = newone;
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		buffer.writeBytes(this.address);
	}

	public InetAddress address() {
		try {
			return InetAddress.getByAddress(this.address);
		} catch (UnknownHostException e) {
			return null;
		}
	}

	public void address(Inet6Address v6address) {
		this.address = v6address.getAddress();
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(super.toString());
		stb.append(' ');
		InetAddress ia = address();
		if (ia == null) {
			stb.append("null");
		} else {
			stb.append(ia.getHostAddress());
		}
		return stb.toString();
	}
}
