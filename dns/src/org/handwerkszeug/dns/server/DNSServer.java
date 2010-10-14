package org.handwerkszeug.dns.server;

import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import werkzeugkasten.common.util.Disposable;
import werkzeugkasten.common.util.Initializable;

public class DNSServer implements Initializable, Disposable {

	protected ChannelFactory factory;
	protected ServerBootstrap bootstrap;
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
		this.factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		this.bootstrap = new ServerBootstrap(this.factory);
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
		// TODO unimplemented
		// bootstrap.setOption("localAddress", "");
		this.bootstrap.setPipelineFactory(new DNSServerPipelineFactory());
		this.group.add(this.bootstrap.bind());
	}

	@Override
	public void dispose() {
		try {
			this.group.close().awaitUninterruptibly();
		} finally {
			this.factory.releaseExternalResources();
		}
	}
}
