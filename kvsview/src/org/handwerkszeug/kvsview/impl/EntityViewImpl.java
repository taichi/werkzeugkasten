package org.handwerkszeug.kvsview.impl;

import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.kvsview.EntityView;
import org.handwerkszeug.kvsview.Filter;
import org.handwerkszeug.kvsview.model.pb.ModelPB;

import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;

public class EntityViewImpl<V> implements EntityView<V> {

	static final String SYSTEM_ROOT = getSystemRootKey();

	static final String getSystemRootKey() {
		// 後で拡張可能にしる。
		return "SYSTEM_ROOT";
	}

	StoreClient<String, ModelPB.Entity> entityStore;
	StoreClient<String, ModelPB.Node> nodeStore;

	public EntityViewImpl(StoreClient<String, ModelPB.Entity> store) {
		this.entityStore = store;
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
	public Iterable<Versioned<V>> getAllEntities(Class<V> entity,
			Filter<V> filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<V> getAllEntityValues(Class<V> entity, Filter<V> filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
