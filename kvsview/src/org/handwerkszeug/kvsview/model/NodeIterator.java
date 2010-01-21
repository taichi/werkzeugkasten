package org.handwerkszeug.kvsview.model;

import java.util.Iterator;

import voldemort.annotations.concurrency.NotThreadsafe;

@NotThreadsafe
public class NodeIterator<V> implements Iterator<V> {

	Node<V> currentNode;
	Iterator<Leaf<V>> leafIterator;
	Leaf<V> current;

	public NodeIterator(EntityRoot<V> initial) {
		Iterator<Node<V>> i = initial.children().iterator();
		if (i.hasNext()) {
			dig(i.next());
		}
	}

	protected boolean dig(final Node<V> node) {
		Node<V> n = node;
		while (n != null && n.hasLeaf() == false) {
			n = n.firstChild();
		}
		if (n != null) {
			this.currentNode = n;
			this.leafIterator = this.currentNode.leaves().iterator();
			return true;
		}
		return false;
	}

	@Override
	public boolean hasNext() {
		if (this.currentNode == null || this.leafIterator == null) {
			return false;
		}
		if (this.leafIterator.hasNext()) {
			return true;
		}
		Node<V> next = this.currentNode.next();
		if (dig(next)) {
			return true;
		}

		Node<V> parent = this.currentNode.parent();
		if (parent != null) {
			Node<V> uncle = parent.next();
			if (dig(uncle)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public V next() {
		this.current = this.leafIterator.next();
		return this.current.value();
	}

	@Override
	public void remove() {
		this.current.remove();
	}
}
