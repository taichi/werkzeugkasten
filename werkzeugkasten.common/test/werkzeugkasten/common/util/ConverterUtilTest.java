package werkzeugkasten.common.util;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.net.URL;

import org.junit.Test;

public class ConverterUtilTest {

	@Test
	public void testConvert() {
		String s = "30";
		assertEquals(30, ConverterUtil.convert(s, Integer.class));
		s = "30.1";
		assertEquals(30.1f, ConverterUtil.convert(s, Float.class));

		s = "300000000000000000";
		assertEquals(new BigInteger(s), ConverterUtil.convert(s,
				BigInteger.class));

		s = "file://" + this.getClass().getName();
		assertEquals(s, ConverterUtil.convert(s, URL.class).toExternalForm());
	}

}
