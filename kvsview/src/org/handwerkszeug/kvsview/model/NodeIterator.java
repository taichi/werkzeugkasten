package org.handwerkszeug.kvsview.model;

import java.util.Iterator;

import voldemort.annotations.concurrency.NotThreadsafe;
import voldemort.versioning.Versioned;

@NotThreadsafe
public class NodeIterator<V> implements Iterator<Versioned<V>> {

	protected Node<Versioned<V>> currentNode;
	protected Iterator<Leaf<Versioned<V>>> leafIterator;
	protected Leaf<Versioned<V>> current;

	public NodeIterator(EntityRoot<Versioned<V>> initial) {
		Iterator<Node<Versioned<V>>> i = initial.children().iterator();
		if (i.hasNext()) {
			dig(i.next());
		}
	}

	protected boolean dig(final Node<Versioned<V>> node) {
		Node<Versioned<V>> n = node;
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
		Node<Versioned<V>> next = this.currentNode.next();
		if (dig(next)) {
			return true;
		}

		Node<Versioned<V>> parent = this.currentNode.parent();
		if (parent != null) {
			Node<Versioned<V>> uncle = parent.next();
			if (dig(uncle)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Versioned<V> next() {
		this.current = this.leafIterator.next();
		return this.current.value();
	}

	@Override
	public void remove() {
		this.current.remove();
	}
}
