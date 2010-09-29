package org.handwerkszeug.dns.client;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.dns.DNSClass;
import org.handwerkszeug.dns.Type;

public class DNSClient {

	protected List<String> names = new ArrayList<String>();

	protected DNSClass dnsclass = DNSClass.IN;

	protected Type type = Type.A;

	protected InetSocketAddress serverAddress;

	protected InetSocketAddress clientAddress;

	protected int serverPort;

	public static void main(String[] args) {

	}
}
