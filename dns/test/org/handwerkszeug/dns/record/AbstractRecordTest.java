package org.handwerkszeug.dns.record;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.handwerkszeug.dns.NameCompressor;
import org.jboss.netty.buffer.ChannelBuffer;
import org.junit.Before;
import org.junit.Test;

public class AbstractRecordTest {

	class R extends AbstractRecord {
		public R() {
			super(null);
		}

		@Override
		protected void parseRDATA(ChannelBuffer buffer) {

		}

		@Override
		protected void writeRDATA(ChannelBuffer buffer,
				NameCompressor compressor) {

		}
	}

	AbstractRecord target;

	@Before
	public void setUp() {
		this.target = new R();
	}

	@Test
	public void testToArray() {
		String begin = "\"\\\\\\\"ab\\127\"";
		byte[] actual = this.target.toArrayFromQuoted(begin);
		byte[] exp = new byte[] { '\\', '"', 'a', 'b', 127 };
		assertArrayEquals(exp, actual);

		String act = this.target.toQuoteString(actual).toString();
		assertEquals(begin, act);

	}
}
