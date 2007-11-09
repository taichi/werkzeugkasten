package werkzeugkasten.launcher;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.eclipse.jdt.core.IJavaProject;
import org.junit.Test;

public class LaunchConfigurationFacetRegistryTest {

	@Test
	public void testFind() {
		final Tester t = new Tester();
		List<R> rs = new ArrayList<R>();
		final long now = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			final int count = i;
			rs.add(new R() {
				@Override
				public void run() {
					try {
						Thread.sleep(0, 10 - count);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					LaunchConfigurationFacet f = t.find(String.valueOf(System
							.nanoTime()
							- now));
					StringBuilder stb = new StringBuilder();
					new Formatter(stb).format("%1$d %2$s %3$s", System
							.nanoTime()
							- now, f.getType(), Thread.currentThread()
							.getName());
					msg = stb.toString();
				}
			});
		}

		List<Thread> list = new ArrayList<Thread>();
		for (R r : rs) {
			list.add(new Thread(r));
		}

		for (Thread th : list) {
			th.start();
		}
		for (Thread th : list) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (R r : rs) {
			System.out.println(r.msg);
		}
		assertTrue(t.called);
	}

	private abstract class R implements Runnable {
		String msg = "";
	}

	private class Tester extends LaunchConfigurationFacetRegistry {
		volatile boolean called = false;

		public Tester() {
			super("aaa", "bbb");
		}

		@Override
		public LaunchConfigurationFacet find(final String key) {
			super.find(key);
			return new LaunchConfigurationFacet() {
				@Override
				public void addLibrary(IJavaProject project) {
				}

				public LaunchConfigurationBuilder getBuilder() {
					return null;
				};

				@Override
				public String getDescription() {
					return null;
				}

				public String getType() {
					if (called == false) {
						throw new AssertionError();
					}
					return key;
				}

				@Override
				public void removeLibrary(IJavaProject project) {

				}
			};
		}

		@Override
		protected void initialize() {
			if (called && initialized.get()) {
				throw new AssertionError();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			called = true;
		}
	}

}
