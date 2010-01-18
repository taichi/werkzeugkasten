package org.handwerkszeug;
import java.util.Properties;

import voldemort.server.VoldemortConfig;
import voldemort.server.VoldemortServer;
import voldemort.store.bdb.BdbStorageConfiguration;
import voldemort.store.memory.CacheStorageConfiguration;
import voldemort.store.memory.InMemoryStorageConfiguration;
import voldemort.store.readonly.ReadOnlyStorageConfiguration;

public class VoldemortBootstrap {

	public static void main(String[] args) {
		final VoldemortServer server = start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				VoldemortBootstrap.stop(server);
			}
		});
	}

	public static VoldemortServer start() {
		Properties prop = new Properties();
		prop.setProperty("node.id", "0");
		prop.setProperty("voldemort.home", "./");
		prop.setProperty("http.enable", "false");

		prop.put("storage.configs", BdbStorageConfiguration.class.getName()
				+ ", " + InMemoryStorageConfiguration.class.getName() + ", "
				+ CacheStorageConfiguration.class.getName() + ", "
				+ ReadOnlyStorageConfiguration.class.getName());
		VoldemortConfig config = new VoldemortConfig(prop);
		VoldemortServer server = new VoldemortServer(config);
		server.start();
		return server;
	}

	public static void stop(VoldemortServer server) {
		if (server != null && server.isStarted()) {
			server.stop();
		}
	}
}
