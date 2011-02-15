package org.handwerkszeug.dns.zone;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.ResourceRecord;
import org.handwerkszeug.dns.ZoneType;
import org.junit.Test;

public class ZoneDatabaseTest {

	@Test
	public void testFind() {
		ZoneDatabase db = new ZoneDatabase();
		db.add(new TestZone("example.com."));
		db.add(new TestZone("example.co.jp."));
		db.add(new TestZone("jp."));
		db.add(new TestZone("ne.jp."));

		assertNotNull(db.find(new Name("jp.")));
		assertNull(db.find(new Name("com.")));

	}

	class TestZone extends AbstractZone {
		public TestZone(String name) {
			super(ZoneType.master, new Name(name));
		}

		@Override
		public List<ResourceRecord> resolve(ResourceRecord query) {
			throw new UnsupportedOperationException();
		}
	}
}
