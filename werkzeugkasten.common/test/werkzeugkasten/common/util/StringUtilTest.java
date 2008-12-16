package werkzeugkasten.common.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testReplaceStringStringString() {
		String txt = "abbbcccbbcc";
		assertEquals("abbzzccbzzc", StringUtil.replace(txt, "bc", "zz"));
	}

}
