package org.handwerkszeug.kvsview.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.kvsview.model.Leaf;
import org.handwerkszeug.kvsview.model.Node;
import org.handwerkszeug.kvsview.model.pb.ModelPB;

import voldemort.client.StoreClient;

public class PBNode<V> implements Node<V> {

	protected ModelPB.Node delegate;

	protected StoreClient<String, ModelPB.Node> nodeClient;
	protected StoreClient<String, ModelPB.Leaf> leafClient;
	protected StoreClient<String, V> client;

	public PBNode(ModelPB.Node delegate,
			StoreClient<String, ModelPB.Node> nodeClient,
			StoreClient<String, ModelPB.Leaf> leafClient,
			StoreClient<String, V> client) {
		this.delegate = delegate;
		this.nodeClient = nodeClient;
		this.leafClient = leafClient;
		this.client = client;
	}

	@Override
	public boolean hasLeaf() {
		return 0 < this.delegate.getLeavesCount();
	}

	@Override
	public List<Leaf<V>> leaves() {
		List<Leaf<V>> result = new ArrayList<Leaf<V>>();
		if (this.hasLeaf()) {
			for (ModelPB.Leaf pbl : this.delegate.getLeavesList()) {
				Leaf<V> l = new PBLeaf<V>(pbl, this.client);
				result.add(l);
			}
		}
		return result;
	}

	@Override
	public Node<V> firstChild() {
		return node(this.delegate.getFirstChild());
	}

	@Override
	public Node<V> next() {
		return node(this.delegate.getNext());
	}

	@Override
	public Node<V> parent() {
		return node(this.delegate.getParent());
	}

	@Override
	public Node<V> prev() {
		return node(this.delegate.getPrev());
	}

	protected Node<V> node(String key) {
		ModelPB.Node d = this.nodeClient.getValue(key);
		return new PBNode<V>(d, this.nodeClient, this.leafClient, this.client);
	}
}
