package org.handwerkszeug.dns.server;

import java.util.NavigableSet;

import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.ResolveContext;
import org.handwerkszeug.dns.ResourceRecord;

public class ReferralResponse extends DefaultResponse {
	final NavigableSet<ResourceRecord> nsRecords;

	public ReferralResponse(NavigableSet<ResourceRecord> records) {
		super(RCode.NoError);
		this.nsRecords = records;
	}

	@Override
	public void postProcess(ResolveContext context) {
		context.response().authority().addAll(this.nsRecords);
	}
}