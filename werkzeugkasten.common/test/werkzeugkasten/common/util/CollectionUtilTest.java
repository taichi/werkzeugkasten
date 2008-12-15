package werkzeugkasten.common.util;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

public class CollectionUtilTest {

	@Test
	public void testToList() {
		String[] ary = { "a", "b", "c" };
		List<?> list = CollectionUtil.toList(ary);
		assertEquals(Arrays.asList(ary), list);
		list = CollectionUtil.toList(new TreeSet<Object>(list));
		assertEquals(Arrays.asList(ary), list);

		list = CollectionUtil.toList(new LinkedList<Object>(list));
		assertEquals(Arrays.asList(ary), list);

		ary = new String[] { "a" };
		list = CollectionUtil.toList("a");
		assertEquals(Arrays.asList(ary), list);
	}
}
