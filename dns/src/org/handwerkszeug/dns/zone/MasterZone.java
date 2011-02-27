package org.handwerkszeug.dns.zone;

import static org.handwerkszeug.util.Validation.notNull;

import java.util.NavigableSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.Response;
import org.handwerkszeug.dns.ZoneType;
import org.handwerkszeug.dns.record.SOARecord;
import org.handwerkszeug.dns.server.CNAMEResponse;
import org.handwerkszeug.dns.server.NoErrorResponse;
import org.handwerkszeug.dns.server.NotFoundResponse;

public class MasterZone extends AbstractZone {

	final SOARecord soaRecord;
	final ConcurrentMap<Name, ConcurrentMap<RRType, NavigableSet<ResourceRecord>>> records = new ConcurrentSkipListMap<Name, ConcurrentMap<RRType, NavigableSet<ResourceRecord>>>();

	public MasterZone(Name name, SOARecord soaRecord) {
		super(ZoneType.master, name);
		this.soaRecord = soaRecord;
	}

	@Override
	public Response find(Name qname, RRType qtype) {
		notNull(qname, "qname");
		notNull(qtype, "qtype");

		ConcurrentMap<RRType, NavigableSet<ResourceRecord>> exactMatch = this.records
				.get(qname);
		if (exactMatch != null) {
			NavigableSet<ResourceRecord> rrs = exactMatch.get(qtype);
			if ((rrs != null) && (rrs.isEmpty() == false)) {
				synchronized (rrs) {
					if (rrs.isEmpty() == false) {
						return new NoErrorResponse(rrs);
					}
				}
			}
			if (RRType.CNAME.equals(qtype) == false) {
				rrs = exactMatch.get(RRType.CNAME);
				if (rrs != null) {
					synchronized (rrs) {
						if (rrs.isEmpty() == false) {
							return new CNAMEResponse(rrs.first(), qtype);
						}
					}
				}
			}
		}

		for (Name qn = qname.toParent(); this.name.equals(Name.NULL_NAME) == false; qn = qn
				.toParent()) {
			ConcurrentMap<RRType, NavigableSet<ResourceRecord>> match = this.records
					.get(qn);
			if (match != null) {
				synchronized (match) {
					if (match.isEmpty() == false) {

					}
				}
			}
		}

		// nxdomain
		// nxrrset
		return new NotFoundResponse(this.soaRecord());
	}

	public void add(ResourceRecord rr) {
		notNull(rr, "rr");
		for (;;) {
			ConcurrentMap<RRType, NavigableSet<ResourceRecord>> current = this.records
					.get(rr.name());
			if (current == null) {
				ConcurrentMap<RRType, NavigableSet<ResourceRecord>> newone = new ConcurrentSkipListMap<RRType, NavigableSet<ResourceRecord>>();
				NavigableSet<ResourceRecord> newset = new ConcurrentSkipListSet<ResourceRecord>();
				newset.add(rr);
				newone.put(rr.type(), newset);

				ConcurrentMap<RRType, NavigableSet<ResourceRecord>> prevTypes = this.records
						.putIfAbsent(rr.name(), newone);
				if (prevTypes == null) {
					break;
				}
				synchronized (prevTypes) {
					Set<ResourceRecord> prevRecs = prevTypes.putIfAbsent(
							rr.type(), newset);
					if (prevRecs == null) {
						break;
					}
					prevRecs.add(rr);
					break;
				}
			} else {
				synchronized (current) {
					Set<ResourceRecord> rrs = current.get(rr.type());
					if (rrs == null) {
						NavigableSet<ResourceRecord> newset = new ConcurrentSkipListSet<ResourceRecord>();
						newset.add(rr);
						current.put(rr.type(), newset);
						break;
					}
					if (rrs.isEmpty() == false) {
						rrs.add(rr);
						break;
					}
				}
			}
		}
	}

	public void remove(ResourceRecord rr, boolean checkSets, boolean checkMap) {
		notNull(rr, "rr");
		ConcurrentMap<RRType, NavigableSet<ResourceRecord>> current = this.records
				.get(rr.name());
		if (current != null) {
			synchronized (current) {
				NavigableSet<ResourceRecord> sets = current.get(rr.type());
				sets.remove(rr);
				if (checkSets && sets.isEmpty()) {
					current.remove(rr.type());
					if (checkMap && current.isEmpty()) {
						this.records.remove(rr.name());
					}
				}
			}
		}
	}

	public SOARecord soaRecord() {
		return this.soaRecord;
	}
}
