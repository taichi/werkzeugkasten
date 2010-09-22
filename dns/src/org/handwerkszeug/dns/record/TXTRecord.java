package org.handwerkszeug.dns.record;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.handwerkszeug.dns.NameCompressor;
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
			this.strings.add(readString(part));
		}
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		for (byte[] ary : this.strings) {
			writeString(buffer, ary);
		}
	}

	public String txt() {
		return this.toString();
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		for (Iterator<byte[]> i = this.strings.iterator(); i.hasNext();) {
			stb.append(toQuoteString(i.next()));
			if (i.hasNext()) {
				stb.append(' ');
			}
		}
		return stb.toString();
	}
}
