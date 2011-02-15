package org.handwerkszeug.dns.zone;

import static org.handwerkszeug.util.Validation.notNull;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.Zone;

public class ZoneDatabase {

	protected Map<Name, Zone> zones = new ConcurrentSkipListMap<Name, Zone>();

	public Zone find(Name name) {
		notNull(name, "name");
		Zone result = zones.get(name);
		for (int i = 0, size = zones.size(); result == null && i < size; i++) {
			result = zones.get(name.toParent());
		}
		return result;
	}

	public void add(Zone zone) {
		notNull(zone, "zone");
		this.zones.put(zone.name(), zone);
	}
}
