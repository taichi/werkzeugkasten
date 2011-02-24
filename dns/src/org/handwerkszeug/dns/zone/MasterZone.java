package org.handwerkszeug.dns.zone;

import static org.handwerkszeug.util.Validation.notNull;

import java.util.Set;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.Response;
import org.handwerkszeug.dns.ZoneType;

public class MasterZone extends AbstractZone {

	final ConcurrentNavigableMap<ZoneKey, Set<ResourceRecord>> records = new ConcurrentSkipListMap<MasterZone.ZoneKey, Set<ResourceRecord>>();

	public MasterZone(Name name) {
		super(ZoneType.master, name);
	}

	@Override
	public Response find(Name qname, RRType qtype) {
		notNull(qname, "qname");
		notNull(qtype, "qtype");
		ZoneKey zk = new ZoneKey(qname, qtype);
		Set<ResourceRecord> rrs = this.records.get(zk);

		return null;
	}

	public void add(ResourceRecord rr) {
		notNull(rr, "rr");
		ZoneKey key = new ZoneKey(rr.name(), rr.type());
		for (;;) {
			Set<ResourceRecord> current = this.records.get(key);
			if (current == null) {
				Set<ResourceRecord> newone = new ConcurrentSkipListSet<ResourceRecord>();
				newone.add(rr);
				Set<ResourceRecord> previous = this.records.putIfAbsent(key,
						newone);
				if ((previous == null) || internalAdd(previous, rr)) {
					break;
				}
			} else {
				if (internalAdd(current, rr)) {
					break;
				}
			}
		}
	}

	private boolean internalAdd(Set<ResourceRecord> rrs, ResourceRecord rr) {
		synchronized (rrs) {
			if (rrs.isEmpty()) {// empty set is already removed from map.
				return false;
			} else {
				rrs.add(rr); // duplicate entry has no problem.
				return true;
			}
		}
	}

	public boolean remove(Name name, RRType type) {
		ZoneKey key = new ZoneKey(name, type);
		Set<ResourceRecord> rrs = this.records.remove(key);
		if (rrs != null) {
			synchronized (rrs) {
				rrs.clear(); // mark removed entry.
				return true;
			}
		}
		return false;
	}

	class ZoneKey implements Comparable<ZoneKey> {
		Name name;
		RRType rrtype;

		public ZoneKey(Name name, RRType rrtype) {
			super();
			notNull(name, "name");
			notNull(rrtype, "rrtype");
			this.name = name;
			this.rrtype = rrtype;
		}

		@Override
		public int compareTo(ZoneKey o) {
			int i = this.name.compareTo(o.name);
			return i == 0 ? this.rrtype.compareTo(o.rrtype) : i;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + this.name.hashCode();
			result = prime * result + this.rrtype.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null) {
				return false;
			}
			if (getClass() != o.getClass()) {
				return false;
			}
			return equals((ZoneKey) o);
		}

		public boolean equals(ZoneKey other) {
			return this.name.equals(other.name)
					&& this.rrtype.equals(other.rrtype);
		}
	}
}
