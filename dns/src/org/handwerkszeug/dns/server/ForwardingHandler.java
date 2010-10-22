package org.handwerkszeug.dns.server;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.dns.DNSMessage;
import org.handwerkszeug.dns.RCode;
import org.handwerkszeug.dns.conf.ServerConfiguration;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForwardingHandler extends SimpleChannelUpstreamHandler {

	static final Logger LOG = LoggerFactory.getLogger(ForwardingHandler.class);

	protected ServerConfiguration config;
	protected ChannelFactory clientChannelFactory;

	protected Channel forwading;

	public ForwardingHandler(ServerConfiguration config,
			ChannelFactory clientChannelFactory) {
		this.config = config;
		this.clientChannelFactory = clientChannelFactory;
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, final MessageEvent e)
			throws Exception {
		DNSMessage original = DNSMessage.class.cast(e.getMessage());

		ClientBootstrap bootstrap = new ClientBootstrap(
				this.clientChannelFactory);
		bootstrap.getPipeline().addLast("handler",
				new ClientHanler(original, e.getChannel()));

		List<SocketAddress> newlist = new ArrayList<SocketAddress>(
				this.config.forwarders());
		sendRequest(e, original, bootstrap, newlist);
	}

	protected void sendRequest(final MessageEvent e, final DNSMessage original,
			final ClientBootstrap bootstrap,
			final List<SocketAddress> forwarders) {
		if (0 < forwarders.size()) {
			SocketAddress sa = forwarders.remove(0);
			ChannelFuture f = bootstrap.connect(sa);
			f.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future)
						throws Exception {
					if (future.isSuccess() == false) {
						if (0 < forwarders.size()) {
							// retry.
							sendRequest(e, original, bootstrap, forwarders);
						} else {
							original.header().rcode(RCode.ServFail);
							ChannelBuffer buffer = ChannelBuffers.buffer(512);
							original.write(buffer);
							e.getChannel().write(buffer);
						}
					}
				}
			});
		}
	}

	protected class ClientHanler extends SimpleChannelHandler {

		protected DNSMessage original;

		protected Channel originalChannel;

		public ClientHanler(DNSMessage msg, Channel c) {
			this.original = msg;
			this.originalChannel = c;
		}

		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			ChannelBuffer buffer = ChannelBuffers.buffer(512);
			DNSMessage newone = new DNSMessage();
			newone.header().copy(this.original.header());

			newone.write(buffer);
			e.getChannel().write(buffer);
		}

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
			DNSMessage msg = new DNSMessage(buffer);
			msg.header().id(this.original.header().id());
			ChannelBuffer newone = ChannelBuffers.buffer(buffer.capacity());
			msg.write(newone);
			this.originalChannel.write(newone);
			e.getChannel().close();
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {
			Throwable t = e.getCause();
			LOG.error(t.getLocalizedMessage(), t);
			e.getFuture().setFailure(t);
			e.getChannel().close();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		Throwable t = e.getCause();
		LOG.error(t.getLocalizedMessage(), e);
		e.getChannel().close();
	}
}
