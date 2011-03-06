package org.handwerkszeug.dns.server;

import org.handwerkszeug.dns.DNSMessage;
import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResolveContext;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.Response;
import org.handwerkszeug.dns.record.SingleNameRecord;

public class DNAMEResponse extends DefaultResponse {

	final SingleNameRecord dname;
	final Name qname;
	final RRType qtype;

	public DNAMEResponse(ResourceRecord dname, Name qname, RRType qtype) {
		super(RCode.NoError);
		this.dname = SingleNameRecord.class.cast(dname);
		this.qname = qname;
		this.qtype = qtype;
	}

	@Override
	public void postProcess(ResolveContext context) {
		DNSMessage res = context.response();
		res.answer().add(this.dname);
		Name name = qname.replace(this.dname.name(), this.dname.oneName());
		if (name == null) {
			context.response().header().rcode(RCode.YXDomain);
		} else {
			SingleNameRecord cname = new SingleNameRecord(RRType.CNAME, name);
			cname.name(qname);
			res.answer().add(cname);
			res.header().aa(true);
			Response r = context.resolve(name, qtype);
			r.postProcess(context);
		}
	}

}
