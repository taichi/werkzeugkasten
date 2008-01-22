package aa.bb.cc.map.autogen;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import aa.bb.cc.map.Aaa;

public class AaaMapTest {

	@Test
	public void test() throws Exception {
		Aaa bean = new Aaa();
		AaaMap map = new AaaMap(bean);
		map.put("bbb", 400);
		Assert.assertEquals(400, map.get("bbb"));

		bean.setCcc("hoge");
		Assert.assertEquals("hoge", map.get("ccc"));

		Map<String, String> m = new HashMap<String, String>();
		map.put("ddd", m);
		Assert.assertEquals(m, map.get("ddd"));

	}
}
