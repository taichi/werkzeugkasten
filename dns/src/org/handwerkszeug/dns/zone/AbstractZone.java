package org.handwerkszeug.dns.zone;

import org.handwerkszeug.dns.DNSClass;
import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.Zone;
import org.handwerkszeug.dns.ZoneType;

public abstract class AbstractZone implements Zone {

	protected ZoneType type;

	protected DNSClass dnsclass;

	protected Name name;

	public AbstractZone(ZoneType type, Name name) {
		this(type, DNSClass.IN, name);
	}

	public AbstractZone(ZoneType type, DNSClass dnsclass, Name name) {
		this.type = type;
		this.dnsclass = dnsclass;
		this.name = name;
	}

	@Override
	public ZoneType type() {
		return this.type;
	}

	@Override
	public DNSClass dnsclass() {
		return this.dnsclass;
	}

	@Override
	public Name name() {
		return this.name;
	}

}
