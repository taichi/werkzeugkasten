package org.handwerkszeug.dns;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * 4.1.3. Resource record format
 * 
 * @author taichi
 */
public interface ResourceRecord {

	Type type();

	Name name();

	void name(Name name);

	DNSClass dnsClass();

	void dnsClass(DNSClass dnsClass);

	long ttl();

	void ttl(long ttl);

	int rdlength();

	void rdlength(int rdlength);

	void parse(ChannelBuffer buffer);

	void write(ChannelBuffer buffer, NameCompressor compressor);
}
