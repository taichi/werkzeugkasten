package org.handwerkszeug.kvsview.model.impl;

import org.handwerkszeug.kvsview.model.Leaf;
import org.handwerkszeug.kvsview.model.pb.ModelPB;

import voldemort.client.StoreClient;

public class PBLeaf<V> implements Leaf<V> {

	protected ModelPB.Leaf delegate;

	protected StoreClient<String, V> client;

	public PBLeaf(ModelPB.Leaf delegate, StoreClient<String, V> client) {
		this.delegate = delegate;
		this.client = client;
	}

	@Override
	public V value() {
		String key = this.delegate.getValueKey();
		return this.client.getValue(key);
	}

	@Override
	public void remove() {
		String key = this.delegate.getValueKey();
		this.client.delete(key);
	}

}
