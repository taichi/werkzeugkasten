package org.handwerkszeug.dns.zone;

import java.net.InetAddress;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.record.ARecord;
import org.handwerkszeug.dns.record.SOARecord;
import org.handwerkszeug.dns.record.SingleNameRecord;
import org.junit.Before;
import org.junit.Test;

public class MasterZoneTest {

	MasterZone target;

	@Before
	public void setUp() throws Exception {
		SOARecord soaRecord = new SOARecord();
		Name n = new Name("example.co.jp.");
		soaRecord.name(n);
		soaRecord.mname(new Name("ns.example.co.jp."));
		target = new MasterZone(n, soaRecord);
		SingleNameRecord ns = new SingleNameRecord(RRType.NS, soaRecord.mname());
		ns.name(n);
		target.add(ns);
		target.add(a("www.exaple.co.jp.", "192.168.10.1"));
		target.add(a("example.co.jp.", "192.168.0.1"));
		target.add(a("example.co.jp.", "192.168.100.1"));

	}

	protected ARecord a(String name, String addr) throws Exception {
		ARecord result = new ARecord();
		result.name(new Name(name));
		result.address(InetAddress.getByName(addr));
		return result;
	}

	@Test
	public void testFind() {

	}

}
