package org.handwerkszeug.dns.client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WKPortNumbersTest {
	WKPortNumbers target;

	@Before
	public void setUp() throws Exception {
		this.target = new WKPortNumbers();
	}

	@Test
	public void testLoad() {
		this.target.load();

		assertEquals(WKPortNumbers.UNKNOWN_PORT, this.target.find(3000));
		assertEquals("smpnameres", this.target.find(901));

	}

}
