package org.handwerkszeug.dns.record;

import org.handwerkszeug.dns.DNSClass;
import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.NameCompressor;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.Type;
import org.jboss.netty.buffer.ChannelBuffer;

public abstract class AbstractRecord implements ResourceRecord {

	protected Type type;

	protected Name name;

	protected DNSClass dnsClass;

	protected long ttl;

	protected int rdlength;

	public AbstractRecord(Type type) {
		this.type = type;
	}

	@Override
	public Type type() {
		return this.type;
	}

	@Override
	public Name name() {
		return this.name;
	}

	@Override
	public void name(Name name) {
		this.name = name;
	}

	@Override
	public DNSClass dnsClass() {
		return this.dnsClass;
	}

	@Override
	public void dnsClass(DNSClass dnsClass) {
		this.dnsClass = dnsClass;
	}

	@Override
	public long ttl() {
		return this.ttl;
	}

	@Override
	public void ttl(long ttl) {
		this.ttl = ttl;
	}

	@Override
	public int rdlength() {
		return this.rdlength;
	}

	@Override
	public void rdlength(int value) {
		this.rdlength = value;
	}

	@Override
	public void parse(ChannelBuffer buffer) {
		this.dnsClass = DNSClass.valueOf(buffer.readUnsignedShort());
		this.ttl = buffer.readUnsignedInt();
		this.rdlength = buffer.readUnsignedShort();

		parseRDATA(buffer);
	}

	protected abstract void parseRDATA(ChannelBuffer buffer);

	@Override
	public void write(ChannelBuffer buffer, NameCompressor compressor) {
		// TODO not implemented.
		writeRDATA(buffer, compressor);
	}

	protected abstract void writeRDATA(ChannelBuffer buffer,
			NameCompressor compressor);
}
