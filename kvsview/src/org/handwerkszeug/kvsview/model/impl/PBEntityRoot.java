package org.handwerkszeug.kvsview.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.kvsview.model.EntityRoot;
import org.handwerkszeug.kvsview.model.Node;
import org.handwerkszeug.kvsview.model.pb.ModelPB;

import voldemort.client.StoreClient;

public class PBEntityRoot<V> implements EntityRoot<V> {

	protected ModelPB.EntityRoot rootNode;

	protected StoreClient<String, ModelPB.Node> nodeClient;
	protected StoreClient<String, ModelPB.Leaf> leafClient;
	protected StoreClient<String, V> client;

	public PBEntityRoot(ModelPB.EntityRoot rootNode,
			StoreClient<String, ModelPB.Node> nodeClient,
			StoreClient<String, ModelPB.Leaf> leafClient,
			StoreClient<String, V> client) {
		super();
		this.rootNode = rootNode;
		this.nodeClient = nodeClient;
		this.leafClient = leafClient;
		this.client = client;
	}

	@Override
	public List<Node<V>> children() {
		List<Node<V>> result = new ArrayList<Node<V>>();
		for (String key : rootNode.getRootNodeList()) {
			ModelPB.Node n = this.nodeClient.getValue(key);
			PBNode<V> node = new PBNode<V>(n, nodeClient, leafClient, client);
			result.add(node);
		}
		return result;
	}

	@Override
	public void remove() {
		// TODO そもそも、このオペレーションで何が消えるの？
	}

}
