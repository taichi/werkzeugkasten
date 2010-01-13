import java.util.Properties;

import voldemort.server.VoldemortConfig;
import voldemort.server.VoldemortServer;
import voldemort.store.bdb.BdbStorageConfiguration;
import voldemort.store.memory.CacheStorageConfiguration;
import voldemort.store.memory.InMemoryStorageConfiguration;
import voldemort.store.readonly.ReadOnlyStorageConfiguration;

public class VoldemortBootstrap {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("node.id", "0");
		prop.setProperty("voldemort.home", "./");
		prop.setProperty("http.enable", "false");

		prop.put("storage.configs", BdbStorageConfiguration.class.getName()
				+ ", " + InMemoryStorageConfiguration.class.getName() + ", "
				+ CacheStorageConfiguration.class.getName() + ", "
				+ ReadOnlyStorageConfiguration.class.getName());
		VoldemortConfig config = new VoldemortConfig(prop);
		final VoldemortServer server = new VoldemortServer(config);
		server.start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (server != null && server.isStarted()) {
					server.stop();
				}
			}
		});
	}
}
