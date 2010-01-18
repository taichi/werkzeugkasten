package org.handwerkszeug.kvsview.impl;

import junit.framework.TestCase;

import org.handwerkszeug.VoldemortBootstrap;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.server.VoldemortServer;

public class EntityViewImplTest extends TestCase {

	protected VoldemortServer server;
	protected EntityViewImpl target;

	@Override
	protected void setUp() throws Exception {
		this.server = VoldemortBootstrap.start();
		ClientConfig config = new ClientConfig();
		config.setBootstrapUrls("tcp://localhost:8666/");
		SocketStoreClientFactory factory = new SocketStoreClientFactory(config);
		this.target = new EntityViewImpl(factory);
	}

	@Override
	protected void tearDown() throws Exception {
		VoldemortBootstrap.stop(this.server);
	}

	public void testGetAllEntityValues() {
		fail("Not yet implemented");
	}

}
