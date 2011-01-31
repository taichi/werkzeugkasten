package org.handwerkszeug.dns.record;

import java.net.InetAddress;

import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.client.WKPortNumbers;
import org.handwerkszeug.dns.client.WKProtocols;
import org.handwerkszeug.util.AddressUtil;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 3.4.2. WKS RDATA format
 * 
 * <pre>
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *     |                    ADDRESS                    |
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *     |       PROTOCOL        |                       |
 *     +--+--+--+--+--+--+--+--+                       |
 *     |                                               |
 *     /                   <BIT MAP>                   /
 *     /                                               /
 *     +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * </pre>
 * <p>
 * The WKS record is used to describe the well known services supported by a
 * particular protocol on a particular internet address. The PROTOCOL field
 * specifies an IP protocol number, and the bit map has one bit per port of the
 * specified protocol. The first bit corresponds to port 0, the second to port
 * 1, etc. If the bit map does not include a bit for a protocol of interest,
 * that bit is assumed zero. The appropriate values and mnemonics for ports and
 * protocols are specified in [RFC-1010].
 * </p>
 * 
 * @author taichi
 * 
 */
public class WKSRecord extends AbstractRecord {

	/**
	 * An 32 bit Internet address
	 */
	protected long address;

	/**
	 * An 8 bit IP protocol number
	 */
	protected short protocol;

	/**
	 * A variable length bit map. The bit map must be a multiple of 8 bits long.
	 */
	protected byte[] bitmap;

	public WKSRecord() {
		super(RRType.WKS);
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		this.address = buffer.readUnsignedInt();
		this.protocol = buffer.readUnsignedByte();
		this.bitmap = new byte[rdlength() - 5];// (32bit + 8bit) / 8bit
		buffer.readBytes(this.bitmap);
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		buffer.writeInt((int) this.address);
		buffer.writeByte(this.protocol);
		buffer.writeBytes(this.bitmap);
	}

	public InetAddress address() {
		return AddressUtil.getByAddress(this.address);
	}

	public short protocol() {
		return this.protocol;
	}

	public void protocol(short no) {
		this.protocol = no;
	}

	public byte[] bitmap() {
		return this.bitmap;
	}

	public void bitmap(byte[] bytes) {
		this.bitmap = bytes;
	}

	/**
	 * @see WKProtocols
	 * @see WKPortNumbers
	 */
	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(super.toString());
		stb.append(' ');
		stb.append(this.address().getHostAddress());
		stb.append(' ');
		stb.append(this.protocol());
		return stb.toString();
	}
}
