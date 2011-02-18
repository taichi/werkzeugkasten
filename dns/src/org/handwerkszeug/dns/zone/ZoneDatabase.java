package org.handwerkszeug.dns.zone;

import static org.handwerkszeug.util.Validation.notNull;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.handwerkszeug.dns.DNSClass;
import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.Zone;

public class ZoneDatabase {

	protected Map<ZoneKey, Zone> zones = new ConcurrentSkipListMap<ZoneKey, Zone>();

	public Query prepare(Name name, DNSClass dnsclass) {
		notNull(name, "name");
		notNull(dnsclass, "dnsclass");
		ZoneKey zk = new ZoneKey(name, dnsclass);
		Zone found = this.zones.get(zk);
		if (found != null) {
			// exact match
			return new Query(name, name, dnsclass, found, this);
		}

		Name child = name;
		// partial match
		for (int i = 0, size = this.zones.size(); i < size; i++) {
			Name p = child.toParent();
			zk.name(p);
			found = this.zones.get(zk);
			if (found == null) {
				child = p;
			} else {
				return new Query(name, p, dnsclass, found, this);
			}
		}
		// not found.
		return null;
	}

	public void add(Zone zone/* TODO ZoneConfig? */) {
		notNull(zone, "zone");
		this.zones.put(new ZoneKey(zone), zone);
	}

	static class ZoneKey implements Comparable<ZoneKey> {
		Name name;
		DNSClass dnsclass;

		public ZoneKey(Zone z) {
			this(z.name(), z.dnsclass());
		}

		public ZoneKey(ResourceRecord rr) {
			this(rr.name(), rr.dnsClass());
		}

		public ZoneKey(Name name, DNSClass dnsclass) {
			notNull(name, "name");
			notNull(dnsclass, "dnsclass");
			this.name = name;
			this.dnsclass = dnsclass;
		}

		public Name name() {
			return this.name;
		}

		public void name(Name name) {
			notNull(name, "name");
			this.name = name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + this.dnsclass.hashCode();
			result = prime * result + this.name.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (other == null) {
				return false;
			}
			if (getClass() != other.getClass()) {
				return false;
			}
			return equals((ZoneKey) other);
		}

		public boolean equals(ZoneKey other) {
			return (this.dnsclass == other.dnsclass)
					&& this.name.equals(other.name);
		}

		@Override
		public int compareTo(ZoneKey o) {
			if (o == null) {
				return 1;
			}
			if (equals(o)) {
				return 0;
			}
			return this.hashCode() - o.hashCode();
		}
	}
}
