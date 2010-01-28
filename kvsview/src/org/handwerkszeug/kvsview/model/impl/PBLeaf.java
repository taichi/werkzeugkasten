package org.handwerkszeug.kvsview.model.impl;

import org.handwerkszeug.kvsview.model.Leaf;
import org.handwerkszeug.kvsview.model.pb.ModelPB;

import voldemort.client.StoreClient;
import voldemort.versioning.Versioned;

public class PBLeaf<V> implements Leaf<Versioned<V>> {

	protected ModelPB.Leaf delegate;

	protected StoreClient<String, V> client;

	public PBLeaf(ModelPB.Leaf delegate, StoreClient<String, V> client) {
		this.delegate = delegate;
		this.client = client;
	}

	@Override
	public Versioned<V> value() {
		return this.client.get(valueKey());
	}

	protected String valueKey() {
		return this.delegate.getValueKey();
	}

	@Override
	public void remove() {
		String key = this.delegate.getValueKey();
		this.client.delete(key);
	}

}
