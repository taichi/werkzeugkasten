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
import voldemort.versioning.Versioned;

public class EntityViewImplTest extends TestCase {

	protected VoldemortServer server;
	protected EntityViewImpl target;

	protected List<TestPB.Dept> expectedDept;
	protected List<TestPB.Emp> expectedEmp;

	@Override
	protected void setUp() throws Exception {
		this.server = VoldemortBootstrap.start();
		ClientConfig config = new ClientConfig();
		config.setBootstrapUrls("tcp://localhost:8666/");
		SocketStoreClientFactory factory = new SocketStoreClientFactory(config);
		this.target = new EntityViewImpl(factory);

		this.expectedDept = new ArrayList<TestPB.Dept>();
		this.expectedEmp = new ArrayList<TestPB.Emp>();
		for (int i = 0; i < 10; i++) {
			TestPB.Dept d = TestPB.Dept.newBuilder().setName(
					String.valueOf(i) + "Dept").build();
			this.expectedDept.add(d);
			for (int j = 0; j < 10; j++) {
				TestPB.Emp e = TestPB.Emp.newBuilder().setName(
						String.valueOf(i) + String.valueOf(j) + "Emp").setDept(
						d).build();
				this.expectedEmp.add(e);
			}
		}

		List<String> deptKeys = puts(TestPB.Dept.class, this.expectedDept,
				factory);
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

			ModelPB.EntityRoot root = ModelPB.EntityRoot.newBuilder()
					.addRootNode(leftKey).addRootNode(rightKey).build();
			rootStore.put(rootKey, root);

			List<String> empKeys = puts(TestPB.Emp.class, this.expectedEmp,
					factory);
			Deque<ModelPB.Leaf> empLeaves = leaves(empKeys);

			int size = empLeaves.size();
			int secondryNodeSize = size / 2 / 10;

			String primaryLeftChild = putSecondryKids(nodeStore, leftKey,
					empLeaves, kg, secondryNodeSize);

			String primaryRightChild = putSecondryKids(nodeStore, rightKey,
					empLeaves, kg, secondryNodeSize);

			ModelPB.Node left = ModelPB.Node.newBuilder().setParent(rootKey)
					.setNext(rightKey).setFirstChild(primaryLeftChild).build();

			ModelPB.Node right = ModelPB.Node.newBuilder().setParent(rootKey)
					.setPrev(leftKey).setFirstChild(primaryRightChild).build();

			nodeStore.put(leftKey, left);
			nodeStore.put(rightKey, right);
		}
	}

	private String putSecondryKids(StoreClient<String, ModelPB.Node> nodeStore,
			String leftKey, Deque<ModelPB.Leaf> empLeaves, KeyGenerator kg,
			int secondryNodeSize) {

		List<String> childNodeKey = new ArrayList<String>(secondryNodeSize);
		for (int i = 0; i < secondryNodeSize; i++) {
			childNodeKey.add(kg.nextKey());
		}

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
		return childNodeKey.get(0);
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

	public void testGetAllEntities() throws Exception {
		List<Versioned<TestPB.Dept>> deps = getAllEntities(TestPB.Dept.class);
		List<TestPB.Dept> dlist = strip(deps);
		assertEquals(expectedDept, dlist);
		assertFalse(expectedDept.get(0) == dlist.get(0));
		List<Versioned<TestPB.Emp>> emps = getAllEntities(TestPB.Emp.class);
		List<TestPB.Emp> elist = strip(emps);
		assertEquals(expectedEmp, elist);
		assertFalse(expectedEmp.get(0) == elist.get(0));
	}

	private <V> List<V> strip(List<Versioned<V>> list) {
		List<V> result = new ArrayList<V>();
		for (Versioned<V> v : list) {
			result.add(v.getValue());
		}
		return result;
	}

	private <V> List<Versioned<V>> getAllEntities(Class<V> clazz) {
		Iterable<Versioned<V>> i = target.getAllEntities(clazz,
				new Filter<Versioned<V>>() {
					@Override
					public boolean filter(Versioned<V> v) {
						return true;
					}
				});

		List<Versioned<V>> list = new ArrayList<Versioned<V>>();
		for (Versioned<V> v : i) {
			list.add(v);
		}
		return list;
	}

	public void testGetAllEntityValues() {
		List<TestPB.Dept> deps = getAllvalues(TestPB.Dept.class);
		assertEquals(expectedDept, deps);

		List<TestPB.Emp> emps = getAllvalues(TestPB.Emp.class);
		assertEquals(expectedEmp, emps);
	}

	private <V> List<V> getAllvalues(Class<V> clazz) {
		Iterable<V> i = target.getAllEntityValues(clazz, new Filter<V>() {
			@Override
			public boolean filter(V v) {
				return true;
			}
		});

		List<V> list = new ArrayList<V>();
		for (V v : i) {
			list.add(v);
		}
		return list;
	}
}
