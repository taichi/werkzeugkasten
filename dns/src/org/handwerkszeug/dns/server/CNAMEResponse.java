package org.handwerkszeug.dns.server;

import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResolveContext;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.record.SingleNameRecord;

public class CNAMEResponse extends DefaultResponse {
	final SingleNameRecord cname;
	final RRType qtype;

	public CNAMEResponse(ResourceRecord rr, RRType qtype) {
		super(RCode.NoError);
		this.cname = SingleNameRecord.class.cast(rr);
		this.qtype = qtype;
	}

	@Override
	public void postProcess(ResolveContext context) {
		context.response().answer().add(cname);
		context.resolve(cname.oneName(), qtype);
	}
}