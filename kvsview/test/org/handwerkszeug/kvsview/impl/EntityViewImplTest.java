package org.handwerkszeug.kvsview.impl;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import junit.framework.TestCase;

import org.handwerkszeug.VoldemortBootstrap;
import org.handwerkszeug.kvsview.Filter;
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
			TestPB.Dept d = TestPB.Dept.newBuilder().setName(
					String.valueOf(i) + "Dept").build();
			deps.add(d);
			for (int j = 0; j < 10; j++) {
				TestPB.Emp e = TestPB.Emp.newBuilder().setName(
						String.valueOf(i) + String.valueOf(j) + "Emp").setDept(
						d).build();
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
			// store dept datas.
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
			// store emp datas.
			String rootKey = TestPB.Emp.class.getName();
			String leftKey = kg.nextKey();
			String rightKey = kg.nextKey();
			ModelPB.Node left = ModelPB.Node.newBuilder().setParent(rootKey)
					.setNext(rightKey).build();
			ModelPB.Node right = ModelPB.Node.newBuilder().setParent(rootKey)
					.setPrev(leftKey).build();
			nodeStore.put(leftKey, left);
			nodeStore.put(rightKey, right);

			ModelPB.EntityRoot root = ModelPB.EntityRoot.newBuilder()
					.addRootNode(leftKey).addRootNode(rightKey).build();
			rootStore.put(rootKey, root);

			List<String> empKeys = puts(TestPB.Emp.class, emps, factory);
			Deque<ModelPB.Leaf> empLeaves = leaves(empKeys);
			int size = empLeaves.size();

			int secondryNodeSize = size / 2 / 10;

			List<String> secondryNodes = new ArrayList<String>(secondryNodeSize);
			for (int i = 0; i < secondryNodeSize; i++) {
				secondryNodes.add(kg.nextKey());
			}
			List<String> leftKids = secondryNodes.subList(0,
					secondryNodeSize / 2);
			putSecondryKids(nodeStore, leftKey, empLeaves, leftKids);
			List<String> rightKids = secondryNodes.subList(
					secondryNodeSize / 2, secondryNodes.size());
			putSecondryKids(nodeStore, rightKey, empLeaves, rightKids);
		}
	}

	private void putSecondryKids(StoreClient<String, ModelPB.Node> nodeStore,
			String leftKey, Deque<ModelPB.Leaf> empLeaves,
			List<String> childNodeKey) {
		for (ListIterator<String> i = childNodeKey.listIterator(); i.hasNext();) {
			String current = i.next();
			ModelPB.Node.Builder builder = ModelPB.Node.newBuilder();
			builder.setParent(leftKey);
			if (i.hasPrevious()) {
				String prev = childNodeKey.get(i.previousIndex());
				builder.setPrev(prev);
			}
			if (i.hasNext()) {
				String next = childNodeKey.get(i.nextIndex());
				builder.setNext(next);
			}
			for (int j = 0; j < 10; j++) {
				builder.addLeaves(empLeaves.remove());
			}
			nodeStore.put(current, builder.build());
		}
	}

	protected LinkedList<ModelPB.Leaf> leaves(List<String> keys) {
		LinkedList<ModelPB.Leaf> result = new LinkedList<ModelPB.Leaf>();
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

	@Override
	protected void tearDown() throws Exception {
		VoldemortBootstrap.stop(this.server);
	}

	public void testGetAllEntityValues() {
		Iterable<TestPB.Dept> i = target.getAllEntityValues(TestPB.Dept.class,
				new Filter<TestPB.Dept>() {
					public boolean filter(TestPB.Dept v) {
						return true;
					};
				});
		for (TestPB.Dept d : i) {
			System.out.println(d);
		}
	}
}
