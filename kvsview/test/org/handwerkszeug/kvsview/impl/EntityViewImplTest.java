package org.handwerkszeug.kvsview.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.handwerkszeug.VoldemortBootstrap;
import org.handwerkszeug.kvsview.model.pb.KeyGenerator;
import org.handwerkszeug.kvsview.model.pb.ModelPB;
import org.handwerkszeug.kvsview.model.pb.UUIDKeyGenerator;
import org.handwerkszeug.kvsview.pb.TestPB;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
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

		List<TestPB.Dept> deps = new ArrayList<TestPB.Dept>();
		List<TestPB.Emp> emps = new ArrayList<TestPB.Emp>();
		for (int i = 0; i < 10; i++) {
			TestPB.Dept d = dept(String.valueOf(i));
			deps.add(d);
			for (int j = 0; j < 10; j++) {
				TestPB.Emp e = emp(String.valueOf(i) + String.valueOf(j), d);
				emps.add(e);
			}
		}

		List<String> deptKeys = puts(TestPB.Dept.class, deps, factory);
		List<ModelPB.Leaf> deptLeaves = leaves(deptKeys);

		KeyGenerator kg = new UUIDKeyGenerator();
		StoreClient<String, ModelPB.Node> nodeStore = factory
				.getStoreClient("node");
		StoreClient<String, ModelPB.EntityRoot> rootStore = factory
				.getStoreClient("entityRoot");

		{
			String nodeKey = kg.nextKey();
			String rootKey = TestPB.Dept.class.getName();
			ModelPB.Node node = ModelPB.Node.newBuilder().setParent(rootKey)
					.addAllLeaves(deptLeaves).build();
			ModelPB.EntityRoot root = ModelPB.EntityRoot.newBuilder()
					.addRootNode(nodeKey).build();
			nodeStore.put(nodeKey, node);
			rootStore.put(rootKey, root);
		}
		{
			String rootKey = TestPB.Emp.class.getName();
			String leftKey = kg.nextKey();
			String rightKey = kg.nextKey();

			ModelPB.EntityRoot root = ModelPB.EntityRoot.newBuilder()
					.addRootNode(leftKey).addRootNode(rightKey).build();
			rootStore.put(rootKey, root);

			List<String> empKeys = puts(TestPB.Emp.class, emps, factory);
			List<ModelPB.Leaf> empLeaves = leaves(empKeys);
			int size = empLeaves.size();

			int secondryNodeSize = size / 2 / 10;

			List<String> secondryNodes = new ArrayList<String>();
			for (int i = 0; i < secondryNodeSize; i++) {
				secondryNodes.add(kg.nextKey());
			}

			List<ModelPB.Leaf> left = empLeaves.subList(0, size / 2);
			List<ModelPB.Leaf> right = empLeaves.subList(size / 2, size);

		}
	}

	protected List<ModelPB.Leaf> leaves(List<String> keys) {
		List<ModelPB.Leaf> result = new ArrayList<ModelPB.Leaf>();
		for (String key : keys) {
			ModelPB.Leaf l = ModelPB.Leaf.newBuilder().setValueKey(key).build();
			result.add(l);
		}
		return result;
	}

	protected <V> List<String> puts(Class<V> clazz, List<V> list,
			StoreClientFactory factory) {
		List<String> result = new ArrayList<String>();
		StoreClient<String, V> store = factory.getStoreClient(clazz.getName());
		KeyGenerator kg = new UUIDKeyGenerator();
		for (V v : list) {
			String k = kg.nextKey();
			result.add(k);
			store.put(k, v);
		}
		return result;
	}

	protected TestPB.Dept dept(String seed) {
		TestPB.Dept result = TestPB.Dept.newBuilder().setName(seed + "Dept")
				.build();
		return result;
	}

	protected TestPB.Emp emp(String seed, TestPB.Dept d) {
		TestPB.Emp result = TestPB.Emp.newBuilder().setName(seed + "Emp")
				.setDept(d).build();
		return result;
	}

	@Override
	protected void tearDown() throws Exception {
		VoldemortBootstrap.stop(this.server);
	}

	public void testGetAllEntityValues() {
		fail("Not yet implemented");
	}

}
