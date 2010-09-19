package org.handwerkszeug.dns.record;

import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.RecordUtil;
import org.handwerkszeug.dns.Type;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 3.3.14. TXT RDATA format
 * 
 * @author taichi
 * 
 */
public class TXTRecord extends AbstractRecord {

	protected List<byte[]> strings;

	public TXTRecord() {
		super(Type.TXT);
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		this.strings = new ArrayList<byte[]>();
		ChannelBuffer part = buffer.readSlice(rdlength());
		while (part.readable()) {
			this.strings.add(RecordUtil.readString(part));
		}
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		// TODO Auto-generated method stub

	}

}
