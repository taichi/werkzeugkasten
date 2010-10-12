package org.handwerkszeug.dns.client;

import org.junit.Test;

public class DNSClientTest {

	@Test
	public void main() throws Exception {
		String[] args = { "iana.org", "AAAA", "IN" };
		DNSClient.main(args);
	}
}
