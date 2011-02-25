package org.handwerkszeug.dns.zone;

import static org.handwerkszeug.util.Validation.notNull;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.handwerkszeug.dns.DNSMessage;
import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.Response;
import org.handwerkszeug.dns.ZoneType;
import org.handwerkszeug.dns.record.SingleNameRecord;

public class MasterZone extends AbstractZone {

	final ConcurrentMap<Name, ConcurrentMap<RRType, Set<ResourceRecord>>> records = new ConcurrentSkipListMap<Name, ConcurrentMap<RRType, Set<ResourceRecord>>>();

	public MasterZone(Name name) {
		super(ZoneType.master, name);
	}

	@Override
	public Response find(Name qname, RRType qtype) {
		notNull(qname, "qname");
		notNull(qtype, "qtype");

		ConcurrentMap<RRType, Set<ResourceRecord>> exactMatch = this.records
				.get(qname);
		if (exactMatch != null) {
			Set<ResourceRecord> rrs = exactMatch.get(qtype);
			if ((rrs != null) && (rrs.isEmpty() == false)) {
				if (RRType.CNAME.equals(qtype)) {
					return new NoErrorResponse(rrs);
				} else {
					Set<ResourceRecord> ans = new HashSet<ResourceRecord>();
					for (ResourceRecord rr : rrs) {

					}
				}
			}

		}

		for (Name n = qname; this.name.equals(Name.NULL_NAME) == false; n = n
				.toParent()) {

		}

		// delegation
		// nxdomain
		// success
		// nxrrset
		return null;
	}

	static abstract class DefaultResponse implements Response {
		final RCode rcode;

		protected DefaultResponse(RCode rcode) {
			this.rcode = rcode;
		}

		@Override
		public RCode rcode() {
			return this.rcode;
		}
	}

	class NoErrorResponse extends DefaultResponse {
		final Set<ResourceRecord> records;

		public NoErrorResponse(Set<ResourceRecord> records) {
			super(RCode.NoError);
			this.records = records;
		}

		@Override
		public void postProcess(DNSMessage responseMessage) {
			responseMessage.answer().addAll(this.records);
		}
	}

	class RecursiveResponse extends DefaultResponse {
		final Set<SingleNameRecord> records;

		public RecursiveResponse(Set<SingleNameRecord> records) {
			super(RCode.NoError);
			this.records = records;
		}

		@Override
		public void postProcess(DNSMessage responseMessage) {

		}
	}

	public void add(ResourceRecord rr) {
		notNull(rr, "rr");
		for (;;) {
			ConcurrentMap<RRType, Set<ResourceRecord>> current = this.records
					.get(rr.name());
			if (current == null) {
				ConcurrentMap<RRType, Set<ResourceRecord>> newone = new ConcurrentSkipListMap<RRType, Set<ResourceRecord>>();
				Set<ResourceRecord> newset = new ConcurrentSkipListSet<ResourceRecord>();
				newset.add(rr);
				newone.put(rr.type(), newset);

				ConcurrentMap<RRType, Set<ResourceRecord>> prevTypes = this.records
						.putIfAbsent(rr.name(), newone);
				if (prevTypes == null) {
					break;
				}
				Set<ResourceRecord> prevRecs = prevTypes.putIfAbsent(rr.type(),
						newset);
				if (prevRecs == null) {
					break;
				}
				prevRecs.add(rr);
				break;
			} else {
				synchronized (current) {
					Set<ResourceRecord> rrs = current.get(rr.type());
					if ((rrs != null) && (rrs.isEmpty() == false)) {
						rrs.add(rr);
						break;
					}
				}
			}
		}
	}

	public void remove(ResourceRecord rr, boolean checkSets, boolean checkMap) {
		notNull(rr, "rr");
		ConcurrentMap<RRType, Set<ResourceRecord>> current = this.records
				.get(rr.name());
		if (current != null) {
			synchronized (current) {
				Set<ResourceRecord> sets = current.get(rr.type());
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
}
