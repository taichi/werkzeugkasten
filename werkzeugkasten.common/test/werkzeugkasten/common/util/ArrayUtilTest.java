package werkzeugkasten.common.util;

import static org.junit.Assert.assertEquals;
import static werkzeugkasten.common.util.ArrayUtil.compare;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testCompare() throws Exception {
		assertEquals(0, compare(null, null));
		assertEquals(-1, compare(null, new byte[0]));
		assertEquals(1, compare(new byte[0], null));
		assertEquals(-1, compare(new byte[0], new byte[1]));
		assertEquals(1, compare(new byte[1], new byte[0]));
		assertEquals(0, compare(new byte[] { 1 }, new byte[] { 1 }));
		assertEquals(1, compare(new byte[] { 1, 2 }, new byte[] { 1, 1 }));
		byte[] aa = new byte[] { 'a', 'a' };
		byte[] zzzz = new byte[] { 'z', 'z', 'z', 'z' };
		assertEquals(-1, compare(aa, zzzz));
	}
}
