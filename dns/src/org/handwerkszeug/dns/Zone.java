package org.handwerkszeug.dns;

import java.util.List;

public interface Zone {
	Name name();

	DNSClass dnsclass();

	ZoneType type();

	List<ResourceRecord> resolve(ResourceRecord query);
}
