package org.handwerkszeug.dns.record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResourceRecord;
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
		super(RRType.TXT);
	}

	public TXTRecord(TXTRecord from) {
		super(from);
		if (from.strings != null) {
			List<byte[]> newone = new ArrayList<byte[]>(from.strings.size());
			for (byte[] b : from.strings) {
				newone.add(Arrays.copyOf(b, b.length));
			}
		}
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

	@Override
	protected ResourceRecord newInstance() {
		return new TXTRecord(this);
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
