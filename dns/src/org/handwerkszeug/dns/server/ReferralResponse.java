package org.handwerkszeug.dns.server;

import java.util.Set;

import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.ResolveContext;
import org.handwerkszeug.dns.ResourceRecord;

public class ReferralResponse extends DefaultResponse {
	final Set<ResourceRecord> nsRecords;

	public ReferralResponse(Set<ResourceRecord> records) {
		super(RCode.NoError);
		this.nsRecords = records;
	}

	@Override
	public void postProcess(ResolveContext context) {
		context.response().authority().addAll(this.nsRecords);
	}
}