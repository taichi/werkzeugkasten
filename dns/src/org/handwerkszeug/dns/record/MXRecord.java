package org.handwerkszeug.dns.record;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResourceRecord;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 3.3.9. MX RDATA format
 * 
 * <pre>
 *                                   1  1  1  1  1  1
 *     0  1  2  3  4  5  6  7  8  9  0  1  2  3  4  5
 *    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *    |                  PREFERENCE                   |
 *    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 *    /                   EXCHANGE                    /
 *    /                                               /
 *    +--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+
 * </pre>
 * 
 * @author taichi
 */
public class MXRecord extends AbstractRecord {

	/**
	 * A 16 bit integer which specifies the preference given to this RR among
	 * others at the same owner. Lower values are preferred.
	 */
	protected int preference;

	/**
	 * RFC 974 the name of a host.
	 */
	protected Name exchange;

	public MXRecord() {
		super(RRType.MX);
	}

	public MXRecord(MXRecord from) {
		super(from);
		this.preference = from.preference();
		this.exchange = from.exchange();
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		this.preference = buffer.readUnsignedShort();
		this.exchange = new Name(buffer);

	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		buffer.writeShort(this.preference);
		this.exchange.write(buffer, compressor);
	}

	@Override
	protected ResourceRecord newInstance() {
		return new MXRecord(this);
	}

	public int preference() {
		return this.preference;
	}

	public Name exchange() {
		return this.exchange;
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(super.toString());
		stb.append(' ');
		stb.append(this.preference());
		stb.append(' ');
		stb.append(this.exchange());
		return stb.toString();
	}
}
