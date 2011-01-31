package org.handwerkszeug.dns.record;

import java.net.InetAddress;

import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.util.AddressUtil;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 3.4.1. A RDATA format
 * 
 * @author taichi
 */
public class ARecord extends AbstractRecord {

	/**
	 * A 32 bit Internet address.
	 */
	protected long address;

	public ARecord() {
		super(RRType.A);
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		this.address = buffer.readUnsignedInt();
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		buffer.writeInt((int) this.address);
	}

	public InetAddress address() {
		return AddressUtil.getByAddress(this.address);
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(super.toString());
		stb.append(' ');
		stb.append(this.address().getHostAddress());
		return stb.toString();
	}
}
