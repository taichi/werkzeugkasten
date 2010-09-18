package org.handwerkszeug.dns;

import static org.junit.Assert.assertEquals;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Before;
import org.junit.Test;

public class NameTest {

	// ; java dig 0.0
	// ;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 55334
	// ;; flags: qr rd ra ; qd: 1 an: 4 au: 4 ad: 8
	// ;; QUESTIONS:
	// ;; google.com., type = MX, class = IN
	//
	// ;; ANSWERS:
	// google.com. 900 IN MX 200 google.com.s9a2.psmtp.com.
	// google.com. 900 IN MX 300 google.com.s9b1.psmtp.com.
	// google.com. 900 IN MX 400 google.com.s9b2.psmtp.com.
	// google.com. 900 IN MX 100 google.com.s9a1.psmtp.com.
	//
	// ;; AUTHORITY RECORDS:
	// google.com. 145245 IN NS ns3.google.com.
	// google.com. 145245 IN NS ns4.google.com.
	// google.com. 145245 IN NS ns2.google.com.
	// google.com. 145245 IN NS ns1.google.com.
	//
	// ;; ADDITIONAL RECORDS:
	// google.com.s9b2.psmtp.com. 11850 IN A 74.125.148.14
	// google.com.s9a1.psmtp.com. 11850 IN A 74.125.148.10
	// google.com.s9a2.psmtp.com. 11850 IN A 74.125.148.11
	// google.com.s9b1.psmtp.com. 11850 IN A 74.125.148.13
	// ns4.google.com. 318014 IN A 216.239.38.10
	// ns2.google.com. 318014 IN A 216.239.34.10
	// ns3.google.com. 318014 IN A 216.239.36.10
	// ns1.google.com. 318014 IN A 216.239.32.10
	//
	// ;; Message size: 362 bytes
	// ;; Query time: 74 ms

	static byte[] data = { 32, 74, -127, -128, 0, 1, 0, 4, 0, 0, 0, 4, 6, 103,
			111, 111, 103, 108, 101, 3, 99, 111, 109, 0, 0, 15, 0, 1, -64, 12,
			0, 15, 0, 1, 0, 0, 3, 76, 0, 26, 1, 44, 6, 103, 111, 111, 103, 108,
			101, 3, 99, 111, 109, 4, 115, 57, 98, 49, 5, 112, 115, 109, 116,
			112, -64, 19, -64, 12, 0, 15, 0, 1, 0, 0, 3, 76, 0, 20, 0, 100, 6,
			103, 111, 111, 103, 108, 101, 3, 99, 111, 109, 4, 115, 57, 97, 49,
			-64, 58, -64, 12, 0, 15, 0, 1, 0, 0, 3, 76, 0, 20, 0, -56, 6, 103,
			111, 111, 103, 108, 101, 3, 99, 111, 109, 4, 115, 57, 97, 50, -64,
			58, -64, 12, 0, 15, 0, 1, 0, 0, 3, 76, 0, 20, 1, -112, 6, 103, 111,
			111, 103, 108, 101, 3, 99, 111, 109, 4, 115, 57, 98, 50, -64, 58,
			-64, 42, 0, 1, 0, 1, 0, 0, 56, 63, 0, 4, 74, 125, -108, 13, -64,
			80, 0, 1, 0, 1, 0, 0, 56, 63, 0, 4, 74, 125, -108, 10, -64, 112, 0,
			1, 0, 1, 0, 0, 56, 63, 0, 4, 74, 125, -108, 11, -64, -112, 0, 1, 0,
			1, 0, 0, 56, 63, 0, 4, 74, 125, -108, 14 };

	Name target;
	ChannelBuffer buffer;

	@Before
	public void setUp() {
		this.buffer = ChannelBuffers.wrappedBuffer(data);
		new Header(this.buffer); // skip reading.
	}

	@Test
	public void testParse() {
		Name name = new Name(this.buffer);

		assertEquals("google.com.", new String(name.name));
	}

	// @Test
	public void test() {
		int google = this.buffer.readUnsignedByte();
		assertEquals(6, google);
		System.out.println(this.buffer.readerIndex());
		byte[] ary = new byte[google];
		this.buffer.readBytes(ary);
		assertEquals("google", new String(ary));

		int com = this.buffer.readUnsignedByte();
		assertEquals(3, com);
		System.out.println(this.buffer.readerIndex());
		ary = new byte[com];
		this.buffer.readBytes(ary);
		assertEquals("com", new String(ary));
		System.out.println(this.buffer.readerIndex());

		int zero = this.buffer.readUnsignedByte();

		assertEquals(0, zero);

		int mx = this.buffer.readUnsignedShort();
		assertEquals(15, mx);

		DNSClass IN = DNSClass.valueOf(this.buffer.readUnsignedShort());
		assertEquals(DNSClass.IN, IN);

		System.out.println(this.buffer.readerIndex());
		this.buffer.readerIndex(13);
		ary = new byte[google];
		this.buffer.readBytes(ary);
		assertEquals("google", new String(ary));

		byte[] aa = new byte[30];
		System.arraycopy(ary, 0, aa, 0, ary.length);
		int pos = ary.length;

		System.arraycopy(ary, 0, aa, pos, ary.length);

		System.out.println(aa);
	}
}
