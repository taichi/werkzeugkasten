package org.handwerkszeug.dns.conf;

import junit.framework.Assert;

import org.junit.Test;

public class AddressNormalizer {
	@Test
	public void normalize() throws Exception {
		String addr = "2001:db8::1";
		String normalized = "2001:db8:0:0:0:0:0:1";

		Assert.assertEquals(normalized, normalize(addr));
		Assert.assertEquals(normalized, normalize(normalized));

		Assert.assertEquals("0:0:0:0:0:ffff:129.144.52.38",
				normalize("::ffff:129.144.52.38"));

		Assert.assertEquals("2001:db8:0:0:0:0:0:0", normalize("2001:db8::"));

		Assert.assertEquals("0:0:0:0:0:0:0:1", normalize("::1"));
		Assert.assertEquals("0:0:0:0:0:0:0:0", normalize("::"));

		// error case
		Assert.assertNull(normalize("0:0:0:0:0:0:0:0:0:0:1"));
		Assert.assertNull(normalize("2001::1::1"));

	}

	protected String normalize(String addr) {
		if ("::".equals(addr)) {
			return "0:0:0:0:0:0:0:0"; // oops!!
		}
		String[] ary = addr.split("::");
		if (2 < ary.length) {
			return null;
		}

		String[] fields = addr.split(":");
		int count = 8;
		for (String s : fields) {
			if (s.isEmpty() == false) {
				count--;
				if (0 < s.indexOf('.')) {
					count--;
				}
			}
		}

		if (count < 0) {
			return null;
		}
		if (count == 0) {
			return addr;
		}

		StringBuilder stb = new StringBuilder();
		for (int i = 0, length = fields.length; i < length; i++) {
			String s = fields[i];
			if (s.isEmpty()) {
				while (0 < count) {
					stb.append('0');
					stb.append(':');
					count--;
				}
			} else {
				stb.append(s);
				if (i + 1 < length) {
					stb.append(':');
				}
			}
		}
		return stb.toString();
	}
}
