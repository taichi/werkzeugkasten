package org.handwerkszeug.dns;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.junit.Before;
import org.junit.Test;

public class NameTest {

	// ;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 64158
	// ;; flags: qr rd ra ; qd: 1 an: 4 au: 4 ad: 4
	// ;; QUESTIONS:
	// ;; google.com., type = MX, class = IN
	//
	// ;; ANSWERS:
	// google.com. 805 IN MX 400 google.com.s9b2.psmtp.com.
	// google.com. 805 IN MX 100 google.com.s9a1.psmtp.com.
	// google.com. 805 IN MX 200 google.com.s9a2.psmtp.com.
	// google.com. 805 IN MX 300 google.com.s9b1.psmtp.com.
	//
	// ;; AUTHORITY RECORDS:
	// google.com. 336761 IN NS ns4.google.com.
	// google.com. 336761 IN NS ns3.google.com.
	// google.com. 336761 IN NS ns2.google.com.
	// google.com. 336761 IN NS ns1.google.com.
	//
	// ;; ADDITIONAL RECORDS:
	// ns1.google.com. 303485 IN A 216.239.32.10
	// ns2.google.com. 303485 IN A 216.239.34.10
	// ns3.google.com. 303485 IN A 216.239.36.10
	// ns4.google.com. 303485 IN A 216.239.38.10
	//
	// ;; Message size: 298 bytes

	static byte[] data = { -6, -98, -127, -128, 0, 1, 0, 4, 0, 4, 0, 4, 6, 103,
			111, 111, 103, 108, 101, 3, 99, 111, 109, 0, 0, 15, 0, 1, -64, 12,
			0, 15, 0, 1, 0, 0, 3, 37, 0, 26, 1, -112, 6, 103, 111, 111, 103,
			108, 101, 3, 99, 111, 109, 4, 115, 57, 98, 50, 5, 112, 115, 109,
			116, 112, -64, 19, -64, 12, 0, 15, 0, 1, 0, 0, 3, 37, 0, 20, 0,
			100, 6, 103, 111, 111, 103, 108, 101, 3, 99, 111, 109, 4, 115, 57,
			97, 49, -64, 58, -64, 12, 0, 15, 0, 1, 0, 0, 3, 37, 0, 20, 0, -56,
			6, 103, 111, 111, 103, 108, 101, 3, 99, 111, 109, 4, 115, 57, 97,
			50, -64, 58, -64, 12, 0, 15, 0, 1, 0, 0, 3, 37, 0, 20, 1, 44, 6,
			103, 111, 111, 103, 108, 101, 3, 99, 111, 109, 4, 115, 57, 98, 49,
			-64, 58, -64, 12, 0, 2, 0, 1, 0, 5, 35, 121, 0, 6, 3, 110, 115, 52,
			-64, 12, -64, 12, 0, 2, 0, 1, 0, 5, 35, 121, 0, 6, 3, 110, 115, 51,
			-64, 12, -64, 12, 0, 2, 0, 1, 0, 5, 35, 121, 0, 6, 3, 110, 115, 50,
			-64, 12, -64, 12, 0, 2, 0, 1, 0, 5, 35, 121, 0, 6, 3, 110, 115, 49,
			-64, 12, -64, -28, 0, 1, 0, 1, 0, 4, -95, 125, 0, 4, -40, -17, 32,
			10, -64, -46, 0, 1, 0, 1, 0, 4, -95, 125, 0, 4, -40, -17, 34, 10,
			-64, -64, 0, 1, 0, 1, 0, 4, -95, 125, 0, 4, -40, -17, 36, 10, -64,
			-82, 0, 1, 0, 1, 0, 4, -95, 125, 0, 4, -40, -17, 38, 10 };

	ChannelBuffer buffer;
	Header header;

	@Before
	public void setUp() {
		this.buffer = ChannelBuffers.wrappedBuffer(data);
		this.header = new Header(this.buffer); // skip reading.
	}

	@Test
	public void testParse() {
		Name name = new Name(this.buffer);

		assertEquals("google.com.", new String(name.name));

		int mx = this.buffer.readUnsignedShort();
		assertEquals(15, mx);

		DNSClass IN = DNSClass.valueOf(this.buffer.readUnsignedShort());
		assertEquals(DNSClass.IN, IN);

		Name n = new Name(this.buffer); // ANSERS SECTION 1st record
		assertEquals("google.com.", new String(n.name));
	}

	@Test
	public void testSplit() {
		byte[] ary = "google.com.".getBytes();
		Name name = new Name(ary);
		List<byte[]> list = name.split();
		assertEquals("google", new String(list.get(0)));
		assertEquals("com", new String(list.get(1)));
	}

	@Test
	public void testWrite() {
		byte[] ary = "google.com.".getBytes();
		Name name = new Name(ary);
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer();
		SimpleNameCompressor compressor = new SimpleNameCompressor();
		name.write(cb, compressor);
		Name actual = new Name(cb);
		assertEquals(name, actual);
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

		this.buffer.readerIndex(28);

		Name n = new Name(this.buffer);
		assertEquals("google.com.", new String(n.name));
	}

	@Test
	public void testErrorString() throws Exception {
		try {
			new Name("w3c.org..");
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
		try {
			StringBuilder stb = new StringBuilder();
			for (int i = 0; i < 64; i++) {
				stb.append("a.");
			}
			new Name(stb.toString());
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
}
