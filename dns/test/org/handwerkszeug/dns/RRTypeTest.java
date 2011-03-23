package org.handwerkszeug.dns;

import org.junit.Before;
import org.junit.Test;

public class RRTypeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testName() throws Exception {
		for (RRType rr : RRType.values()) {
			System.out.printf("|%s%n", rr.name());
		}
		for (RRType rr : RRType.values()) {
			String name = rr.name();
			StringBuilder stb = new StringBuilder();
			char[] ary = name.toCharArray();
			for (int i = 0, l = ary.length; i < l; i++) {
				stb.append("(");
				char c = ary[i];
				stb.append("'");
				stb.append(Character.toLowerCase(c));
				stb.append("'");
				stb.append("|");
				stb.append("'");
				stb.append(c);
				stb.append("'");
				stb.append(")");
			}
			System.out.printf("%s\t:\t%s;%n", name, stb.toString());
		}
	}
}
