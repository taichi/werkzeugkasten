package org.handwerkszeug.dns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class HeaderTest {

	protected Header target;

	@Before
	public void setUp() {
		this.target = new Header();
	}

	@Test
	public void testId() {
		try {
			new Header(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			new Header(65535 + 1);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testQr() {
		target.flags(32768); // 1000 0000 0000 0000
		assertTrue(target.qr());
		target.flags(16384); // 0100 0000 0000 0000
		assertFalse(target.qr());

	}

	@Test
	public void testOpcode() {
		OpCode oc = OpCode.IQUERY;
		target.opcode(oc);
		assertEquals(oc, target.opcode());
	}

	@Test
	public void testAa() {
		target.flags(1024); // 0000 0100 0000 0000
		assertTrue(target.aa());
		target.aa(true);
		assertTrue(target.aa());
		target.aa(false);
		assertFalse(target.aa());
	}

	@Test
	public void testTc() {
		target.flags(512); // 0000 0010 0000 0000
		assertTrue(target.tc());
		target.tc(false);
		assertFalse(target.tc());
		target.tc(true);
		assertTrue(target.tc());
	}

	@Test
	public void testRd() {
		target.flags(256); // 0000 0001 0000 0000
		assertTrue(target.rd());
		target.rd(false);
		assertFalse(target.rd());
		target.rd(true);
		assertTrue(target.rd());
	}

	@Test
	public void testRa() {
		target.flags(128); // 0000 0000 1000 0000
		assertTrue(target.ra());
		target.ra(true);
		assertTrue(target.ra());
		target.ra(false);
		assertFalse(target.ra());
	}

	@Test
	public void testZ() {
		target.flags(0); // 0000 0000 0000 0000
		assertEquals(0, target.z());
	}

	@Test
	public void testRcode() {
		target.flags(4); // 0000 0000 0000 0100
		RCode rc = RCode.NOT_IMPLEMENTED;
		assertEquals(rc, target.rcode());

		rc = RCode.FORMAT_ERROR;
		target.rcode(rc);
		assertEquals(rc, target.rcode());
	}

	@Test
	public void testToString() {
		String exp = ";; ->>HEADER<<- opcode: IQUERY, rcode: FORMAT_ERROR, id: 38246\n"
				+ ";; flags: qr aa tc rd ra ; QUERY: 1000, ANSWER: 2000, AUTHORITY: 3000, ADDITIONAL: 4000";
		target.id(38246);
		target.flags(0xFFFF);
		target.opcode(OpCode.IQUERY);
		target.rcode(RCode.FORMAT_ERROR);
		target.qdcount(1000);
		target.ancount(2000);
		target.nscount(3000);
		target.arcount(4000);
		assertEquals(exp, target.toString());
	}
}
