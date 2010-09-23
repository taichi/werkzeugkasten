package org.handwerkszeug.dns;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.DatagramChannelFactory;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
import org.junit.Before;
import org.junit.Test;

public class ResolverTest {

	@Before
	public void setUp() throws Exception {

	}

	class DNSClientHandler extends SimpleChannelHandler {

		long time;

		@Override
		public void channelConnected(ChannelHandlerContext ctx,
				ChannelStateEvent e) throws Exception {
			DNSMessage msg = new DNSMessage();
			msg.header().opcode(OpCode.QUERY);
			msg.header().rd(true);
			ResourceRecord rr = Type.MX.newRecord();
			rr.name(new Name("iana.org."));
			msg.question().add(rr);
			ChannelBuffer buffer = ChannelBuffers.buffer(512);
			msg.write(buffer);
			time = System.currentTimeMillis();
			e.getChannel().write(buffer);

		}

		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
				throws Exception {
			time = System.currentTimeMillis() - time;
			ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
			DNSMessage msg = new DNSMessage(buffer);
			println(msg);
			e.getChannel().close();
		}

		public void println(DNSMessage msg) {
			StringBuilder stb = new StringBuilder();
			stb.append(msg.header());
			stb.append("\n\n");
			stb.append(";; QUESTION SECTION:");
			stb.append("\n");
			for (ResourceRecord rr : msg.question()) {
				stb.append(";");
				stb.append(rr.name().toString());
				stb.append(' ');
				stb.append(rr.dnsClass().name());
				stb.append(' ');
				stb.append(rr.type().name());
				stb.append("\n");
			}

			stb.append("\n");
			append(stb, ";; ANSWER SECTION:", msg.answer());
			stb.append("\n");
			append(stb, ";; AUTHORITY SECTION:", msg.authority());
			stb.append("\n");
			append(stb, ";; ADDITIONAL SECTION:", msg.additional());
			stb.append("\n");

			stb.append(";; Query time: ");
			stb.append(time);
			stb.append(" msec");
			stb.append("\n");
			stb.append(";; WHEN: " + new Date());
			stb.append("\n");
			stb.append(";; MSG SIZE rcvd: ");
			stb.append(msg.messageSize());
			System.out.println(stb.toString());
		}

		void append(StringBuilder stb, String name, List<ResourceRecord> list) {
			stb.append(name);
			stb.append("\n");
			for (ResourceRecord rr : list) {
				stb.append(rr.toString());
				stb.append("\n");
			}
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
				throws Exception {
			e.getCause().printStackTrace();
		}
	}

	@Test
	public void sendTest() throws Exception {
		// use UDP/IP
		DatagramChannelFactory factory = new NioDatagramChannelFactory(
				Executors.newCachedThreadPool());

		ClientBootstrap bootstrap = new ClientBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				return Channels.pipeline(new DNSClientHandler());
			}
		});
		bootstrap.setOption("broadcast", "false");
		bootstrap.setOption("sendBufferSize", 512);
		bootstrap.setOption("receiveBufferSize", 512);

		String host = "192.168.1.1";
		int port = 53;
		InetSocketAddress address = new InetSocketAddress(host, port);

		ChannelFuture future = bootstrap.connect(address);
		future.awaitUninterruptibly();
		if (!future.isSuccess()) {
			future.getCause().printStackTrace();
		}
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		factory.releaseExternalResources();
	}

}
