package org.handwerkszeug.dns.conf;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.dns.NameServerContainer;
import org.handwerkszeug.dns.NameServerContainerProvider;
import org.handwerkszeug.dns.Zone;
import org.handwerkszeug.util.AddressUtil;
import org.handwerkszeug.yaml.DefaultHandler;
import org.handwerkszeug.yaml.MappingHandler;
import org.handwerkszeug.yaml.SequenceHandler;
import org.handwerkszeug.yaml.YamlNodeAccepter;
import org.handwerkszeug.yaml.YamlNodeHandler;
import org.handwerkszeug.yaml.YamlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.nodes.Node;

import werkzeugkasten.common.util.Streams;

public class ServerConfiguration {

	static final Logger LOG = LoggerFactory
			.getLogger(ServerConfiguration.class);

	protected List<SocketAddress> bindingHosts = new ArrayList<SocketAddress>();

	protected List<SocketAddress> forwarders = new ArrayList<SocketAddress>();

	protected List<Zone> zones = new ArrayList<Zone>();

	protected int threadPoolSize = 10;

	public ServerConfiguration() {
	}

	public void load(final URL url) {
		new Streams.using<BufferedInputStream, Exception>() {
			@Override
			public BufferedInputStream open() throws Exception {
				return new BufferedInputStream(url.openStream());
			}

			@Override
			public void handle(BufferedInputStream stream) throws Exception {
				load(stream);
			}

			@Override
			public void happen(Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	public void load(InputStream in) {
		// TODO not implemented
		YamlNodeHandler<ServerConfiguration> root = createRootHandler();
		YamlNodeAccepter<ServerConfiguration> accepter = new YamlNodeAccepter<ServerConfiguration>(
				root);
		accepter.accept(in, this);

		NameServerContainerProvider provider = new NameServerContainerProvider();
		provider.initialize();
		NameServerContainer container = provider.getContainer();
		container.initialize();
		for (String s : container.nameservers()) {
			LOG.info("nameserver {}", s);
			this.forwarders.add(new InetSocketAddress(s, 53));
		}
	}

	protected YamlNodeHandler<ServerConfiguration> createRootHandler() {
		MappingHandler<ServerConfiguration> root = new MappingHandler<ServerConfiguration>();
		final NodeToAddress node2addr = new NodeToAddress();
		root.add(new SequenceHandler<ServerConfiguration>("bindingHosts",
				new DefaultHandler<ServerConfiguration>() {
					@Override
					public void handle(Node node, ServerConfiguration conf) {
						node2addr.handle(node, conf.getBindingHosts());
					}
				}));
		root.add(new DefaultHandler<ServerConfiguration>("threadPoolSize") {
			@Override
			public void handle(Node node, ServerConfiguration conf) {
				String value = YamlUtil.getStringValue(node);
				conf.threadPoolSize = AddressUtil.toInt(value, 10);
			}
		});
		return root;
	}

	public List<SocketAddress> getBindingHosts() {
		return this.bindingHosts;
	}

	public int getThreadPoolSize() {
		return this.threadPoolSize;
	}

	public List<SocketAddress> getForwarders() {
		return this.forwarders;
	}
}
