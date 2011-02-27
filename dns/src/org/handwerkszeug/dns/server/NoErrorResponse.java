package org.handwerkszeug.dns.server;

import java.util.Set;

import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.ResolveContext;
import org.handwerkszeug.dns.ResourceRecord;

public class NoErrorResponse extends DefaultResponse {
	final Set<ResourceRecord> records;

	public NoErrorResponse(Set<ResourceRecord> records) {
		super(RCode.NoError);
		this.records = records;
	}

	@Override
	public void postProcess(ResolveContext context) {
		context.response().answer().addAll(this.records);
		// TODO additional section ?
	}
}