package werkzeugkasten.common.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class LazyLoadingReferenceTest {

	@Test
	public void loadSimple() throws Exception {
		LazyLoadingReference<String> target = new LazyLoadingReference<String>(
				new LazyLoadingReference.Factory<String>() {
					public String create() {
						return "hoge";
					}
				});
		assertEquals("hoge", target.get());
	}

	@Test
	public void loadHeavy() throws Exception {
		final LazyLoadingReference<String> target = new LazyLoadingReference<String>(
				new LazyLoadingReference.Factory<String>() {
					public String create() {
						return "hoge";
					}
				});
		final Random random = new Random(System.currentTimeMillis());
		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < 20; i++) {
			Runnable r = new Runnable() {
				public void run() {
					try {
						long l = random.nextLong() % 100;
						Thread.sleep(l < 0 ? l * -1 : l);
						assertEquals("hoge", target.get());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			Thread t = new Thread(r);
			list.add(t);
			t.start();
		}
		for (Thread t : list) {
			t.join();
		}
	}
}
