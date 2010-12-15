package org.handwerkszeug.dns.conf;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.dns.NameServerContainer;
import org.handwerkszeug.dns.NameServerContainerProvider;
import org.handwerkszeug.dns.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConfiguration {

	static final Logger LOG = LoggerFactory
			.getLogger(ServerConfiguration.class);

	protected List<SocketAddress> bindingHosts = new ArrayList<SocketAddress>();

	protected List<SocketAddress> forwarders = new ArrayList<SocketAddress>();

	protected List<Zone> zones = new ArrayList<Zone>();

	protected int threadPoolSize = Integer.MAX_VALUE;

	public ServerConfiguration() {
	}

	public void load(URL url) {
		// TODO not implemented
		this.bindingHosts.add(new InetSocketAddress("127.0.0.1", 53));

		NameServerContainerProvider provider = new NameServerContainerProvider();
		provider.initialize();
		NameServerContainer container = provider.getContainer();
		container.initialize();
		for (String s : container.nameservers()) {
			LOG.info("nameserver {}", s);
			this.forwarders.add(new InetSocketAddress(s, 53));
		}
	}

	public List<SocketAddress> bindingHosts() {
		return this.bindingHosts;
	}

	public int threadPoolSize() {
		return this.threadPoolSize;
	}

	public List<SocketAddress> forwarders() {
		return this.forwarders;
	}
}
