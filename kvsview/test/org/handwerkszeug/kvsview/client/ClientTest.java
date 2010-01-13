package org.handwerkszeug.kvsview.client;

import org.handwerkszeug.kvsview.entity.Dept;
import org.handwerkszeug.kvsview.entity.Emp;
import org.handwerkszeug.kvsview.model.pb.ModelPB.Entity;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;

public class ClientTest {

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		config.setBootstrapUrls("tcp://localhost:8666/");
		SocketStoreClientFactory factory = new SocketStoreClientFactory(config);
		StoreClient<String, Entity> client = factory.getStoreClient("entity");

		final String key = "SYSTEM_ROOT";

		Class<?>[] classes = new Class[] { Dept.class, Emp.class };

		Entity.Builder builder = Entity.newBuilder();
		for (Class<?> cls : classes) {
			builder.addClassName(cls.getName());
		}
		client.put(key, builder.build());
		try {
			Versioned<Entity> v = client.get(key);
			System.out.println(v.getVersion().toString());
			System.out.println(v.getValue().toString());

			v.setObject(v.getValue().toBuilder().addClassName("aaa.bbb.Ccc")
					.build());
			client.put(key, v);
			v = client.get(key);
			System.out.println(v.getVersion().toString());
			System.out.println(v.getValue().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.delete(key);

	}
}
