package org.handwerkszeug.dns.server;

import org.handwerkszeug.dns.client.DNSClient;
import org.junit.Test;

public class DNSServerTest {

	@Test
	public void testMain() throws Exception {
		DNSServer.main(new String[] {});

		String[] args = { "@127.0.0.1", "iana.org", "AAAA", "IN" };
		DNSClient.main(args);
	}
}
