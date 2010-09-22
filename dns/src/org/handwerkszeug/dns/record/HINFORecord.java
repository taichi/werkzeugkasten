package org.handwerkszeug.dns.record;

import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.Type;
import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 3.3.2. HINFO RDATA format
 * 
 * <p>
 * Standard values for CPU and OS can be found in [RFC-1010].
 * </p>
 * 
 * @author taichi
 * 
 */
public class HINFORecord extends AbstractRecord {

	/**
	 * A <character-string> which specifies the CPU type.
	 */
	protected byte[] cpu;

	/**
	 * A <character-string> which specifies the operating system type.
	 */
	protected byte[] os;

	public HINFORecord() {
		super(Type.HINFO);
	}

	@Override
	protected void parseRDATA(ChannelBuffer buffer) {
		this.cpu = readString(buffer);
		this.os = readString(buffer);
	}

	@Override
	protected void writeRDATA(ChannelBuffer buffer, NameCompressor compressor) {
		writeString(buffer, this.cpu);
		writeString(buffer, this.os);
	}

	public String cpu() {
		return new String(this.cpu);
	}

	public void cpu(String cpu) {
		this.cpu = cpu.getBytes(); // TODO encoding ?
	}

	public String os() {
		return new String(this.os);
	}

	public void os(String os) {
		this.os = os.getBytes(); // TODO encoding ?
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(super.toString());
		stb.append(' ');
		stb.append(this.cpu());
		stb.append(' ');
		stb.append(this.os());
		return stb.toString();
	}
}
