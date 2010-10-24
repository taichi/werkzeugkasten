package org.handwerkszeug.dns.client;

import org.junit.Test;

public class DNSClientTest {

	@Test
	public void main() throws Exception {
		String[] args = { "@127.0.0.1", "iana.org", "AAAA", "IN" };
		DNSClient.main(args);
	}
}
