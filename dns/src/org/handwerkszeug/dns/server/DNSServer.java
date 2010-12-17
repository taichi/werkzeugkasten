package org.handwerkszeug.dns.server;

import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.handwerkszeug.dns.conf.ServerConfiguration;
import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.oio.OioDatagramChannelFactory;
import org.jboss.netty.util.ExternalResourceReleasable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.common.util.Disposable;
import werkzeugkasten.common.util.Initializable;

public class DNSServer implements Initializable, Disposable {

	protected static Logger LOG = LoggerFactory.getLogger(DNSServer.class);

	protected ServerConfiguration config;
	protected ChannelFactory serverChannelFactory;
	protected ChannelFactory clientChannelFactory;
	protected ConnectionlessBootstrap bootstrap;
	protected ChannelGroup group;

	public static void main(String[] args) {
		DNSServer server = new DNSServer();
		server.parseArgs(args);
		server.initialize();
		server.process();
	}

	protected void parseArgs(String[] args) {
		// TODO not implemented
		// find configuration file. maybe named.conf?
	}

	@Override
	public void initialize() {
		LOG.info("initialize");
		this.config = new ServerConfiguration();

		this.config.load(null);
		// TODO from configuration. thread pool size.
		ExecutorService executor = Executors.newCachedThreadPool();
		// TODO TCP?
		this.clientChannelFactory = new OioDatagramChannelFactory(executor);
		// TODO TCP and/or UDP
		this.serverChannelFactory = new OioDatagramChannelFactory(executor);
		ChannelPipelineFactory pipelineFactory = new DNSServerPipelineFactory(
				this.config, this.clientChannelFactory);

		this.bootstrap = new ConnectionlessBootstrap(this.serverChannelFactory);
		this.bootstrap.setPipelineFactory(pipelineFactory);

		this.group = new DefaultChannelGroup();

		Runnable r = new Runnable() {
			@Override
			public void run() {
				dispose();
			}
		};
		Runtime.getRuntime().addShutdownHook(new Thread(r));
	}

	public void process() {
		for (SocketAddress sa : this.config.bindingHosts()) {
			LOG.info("binding {}", sa);
			this.group.add(this.bootstrap.bind(sa));
		}
	}

	@Override
	public void dispose() {
		try {
			this.group.close().awaitUninterruptibly();
		} finally {
			dispose(this.clientChannelFactory);
			dispose(this.serverChannelFactory);
		}
	}

	protected void dispose(ExternalResourceReleasable releasable) {
		try {
			releasable.releaseExternalResources();
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
		}
	}
}
