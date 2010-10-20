package org.handwerkszeug.dns.server;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.Zone;
import org.handwerkszeug.dns.ZoneType;

public abstract class AbstractZone implements Zone {

	protected ZoneType type;

	protected Name name;

	public AbstractZone(ZoneType type, Name name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public ZoneType type() {
		return this.type;
	}

	@Override
	public Name name() {
		return this.name;
	}

}
