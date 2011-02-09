package org.handwerkszeug.dns;

public interface Zone {
	Name name();

	DNSClass dnsclass();

	ZoneType type();

}
