package werkzeugkasten.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileUtilTest {

	protected File testRoot;

	@Before
	public void setUp() throws Exception {
		this.testRoot = new File("tmp");
	}

	@After
	public void tearDown() throws Exception {
		FileUtil.delete(this.testRoot.getPath());
	}

	@Test
	public void testList() {
		List<File> list = FileUtil.list("test/data");
		assertEquals(2, list.size());
		assertEquals("FileUtilTest.txt", list.get(0).getName());
		list = FileUtil.list("test/data3", new FileUtil.NameFilter() {
			public boolean accept(String name) {
				assertNotSame("ing.txt", name);
				return "ING".equals(name) == false;
			}
		});
		assertEquals(1, list.size());
		assertEquals("aa.txt", list.get(0).getName());
	}

	@Test
	public void testCopy() {
		FileUtil.copy("test/data/", testRoot.getName(),
				new FileUtil.NameFilter() {
					public boolean accept(String name) {
						System.out.println(name);
						return true;
					}
				});
		assertTrue(new File(testRoot, "data2/FileUtilTest.txt").exists());
		assertTrue(new File(testRoot, "data2/FileUtilTest2.log.txt").exists());

		File f = new File(this.testRoot, "hoge.xt");
		FileUtil.copy("test/data/data2/FileUtilTest.txt", f.getPath());
		assertTrue(f.exists());
	}
}
