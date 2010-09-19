package org.handwerkszeug.dns.record;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.Type;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * <ul>
 * <li>3.3.1. CNAME RDATA format</li>
 * <li>3.3.11. NS RDATA format</li>
 * <li>3.3.12. PTR RDATA format</li>
 * </ul>
 * 
 * 
 * @author taichi
 */
public class SingleNameRecord extends AbstractRecord {

	protected Name name;

	public SingleNameRecord(Type type) {
		super(type);
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		this.name = new Name(buffer);
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		this.name.write(buffer, compressor);
	}
}
