package org.handwerkszeug.dns.server;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.ResolveContext;
import org.handwerkszeug.dns.ResourceRecord;
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
		// TODO not implemented
	}

}
