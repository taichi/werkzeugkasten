package org.handwerkszeug.dns.zone;

import static org.junit.Assert.assertEquals;

import java.net.InetAddress;

import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.RRType;
import org.handwerkszeug.dns.Response;
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
		this.target = new MasterZone(n, soaRecord);
		SingleNameRecord ns = new SingleNameRecord(RRType.NS, soaRecord.mname());
		ns.name(n);
		this.target.add(ns);
	}

	protected ARecord a(String name, String addr) throws Exception {
		ARecord result = new ARecord();
		result.name(new Name(name));
		result.address(InetAddress.getByName(addr));
		return result;
	}

	protected ARecord a(Name name, InetAddress addr) {
		ARecord a = new ARecord();
		a.name(name);
		a.address(addr);
		return a;
	}

	@Test
	public void testFind() throws Exception {
		this.target.add(a("*.example.co.jp.", "192.168.100.2"));
		this.target.add(a("www.exaple.co.jp.", "192.168.10.1"));
		this.target.add(a("example.co.jp.", "192.168.0.1"));
		this.target.add(a("example.co.jp.", "192.168.100.1"));

		SingleNameRecord ftp = new SingleNameRecord(RRType.NS, new Name(
				"share.example.co.jp."));
		ftp.name(new Name("ftp.example.co.jp."));
		this.target.add(ftp);
		assertEquals(RCode.NXDomain,
				this.target.find(new Name("co.jp."), RRType.ANY).rcode());

		Response res = this.target.find(new Name("example.co.jp"), RRType.A);

	}

}
