package org.handwerkszeug.dns.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.dns.DNSClass;
import org.handwerkszeug.dns.Name;
import org.handwerkszeug.dns.ZoneType;

public class ForwardZone extends AbstractZone {

	protected List<InetAddress> forwarders = new ArrayList<InetAddress>();

	public ForwardZone(Name name) {
		super(ZoneType.forward, name);
	}

	public ForwardZone(DNSClass dnsclass, Name name) {
		super(ZoneType.forward, dnsclass, name);
	}

	public void addForwardHost(InetAddress host) {
		this.forwarders.add(host);
	}

}
