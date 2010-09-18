package org.handwerkszeug.dns;

/**
 * 4.1.3. Resource record format
 * 
 * @author taichi
 */
public interface ResourceRecord {

	Name name();

	int type();

	DNSClass dnsClass();

	int ttl();

	int rdlength();
}
