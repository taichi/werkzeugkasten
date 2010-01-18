package org.handwerkszeug.kvsview.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.handwerkszeug.kvsview.EntityView;
import org.handwerkszeug.kvsview.Filter;
import org.handwerkszeug.kvsview.model.EntityRoot;
import org.handwerkszeug.kvsview.model.NodeIterator;
import org.handwerkszeug.kvsview.model.impl.PBEntityRoot;
import org.handwerkszeug.kvsview.model.pb.ModelPB;

import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import voldemort.versioning.Versioned;

public class EntityViewImpl implements EntityView {

	static final String SYSTEM_ROOT = getSystemRootKey();

	static final String getSystemRootKey() {
		// 後で拡張可能にしる。
		return "SYSTEM_ROOT";
	}

	StoreClientFactory factory;
	StoreClient<String, ModelPB.Entity> entityStore;
	StoreClient<String, ModelPB.EntityRoot> rootStore;
	StoreClient<String, ModelPB.Node> nodeClient;
	StoreClient<String, ModelPB.Leaf> leafClient;

	public EntityViewImpl(StoreClientFactory factory) {
		super();
		this.factory = factory;
		// from store.xml
		this.entityStore = factory.getStoreClient("entity");
		this.rootStore = factory.getStoreClient("entityRoot");
		this.nodeClient = factory.getStoreClient("node");
		this.leafClient = factory.getStoreClient("leaf");
	}

	@Override
	public Iterable<Class<?>> getAccessibleEntities() {
		ModelPB.Entity entity = this.entityStore.getValue(SYSTEM_ROOT);
		List<String> list = entity.getClassNameList();
		List<Class<?>> classes = new ArrayList<Class<?>>(list.size());
		for (String s : list) {
			Class<?> clazz = forName(s);
			classes.add(clazz);
		}
		return classes;
	}

	private Class<?> forName(String s) {
		try {
			Class<?> clazz = Class.forName(s);
			return clazz;
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public <V> Iterable<Versioned<V>> getAllEntities(Class<V> entity,
			Filter<V> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> Iterable<V> getAllEntityValues(Class<V> entity,
			final Filter<V> filter) {
		ModelPB.EntityRoot rootNode = this.rootStore.getValue(entity.getName());
		StoreClient<String, V> store = this.factory.getStoreClient(entity
				.getName());
		final EntityRoot<V> initial = new PBEntityRoot<V>(rootNode,
				this.nodeClient, this.leafClient, store);
		return new Iterable<V>() {
			@Override
			public Iterator<V> iterator() {
				return new FilteringIterator<V>(filter, new NodeIterator<V>(
						initial));
			}
		};
	}

}
