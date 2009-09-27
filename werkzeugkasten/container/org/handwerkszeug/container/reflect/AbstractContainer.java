package org.handwerkszeug.container.reflect;

import java.util.Map;
import java.util.TreeMap;

import org.handwerkszeug.container.Acceptor;
import org.handwerkszeug.container.Container;
import org.handwerkszeug.container.Nameable;

public abstract class AbstractContainer<C extends Nameable, K> extends
		AbstractNameable implements Container<C, K> {

	protected Map<K, C> registry = new TreeMap<K, C>();

	public AbstractContainer(String name) {
		super(name);
	}

	protected void entry(K key, C content) {
		this.registry.put(key, content);
	}

	@Override
	public void walk(Acceptor<C, Boolean> walker) {
		internalWalk(walker);
	}

	protected boolean internalWalk(Acceptor<C, Boolean> walker) {
		for (C rc : this.registry.values()) {
			if (walker.accept(rc) == false) {
				return false;
			}
		}
		return true;
	}

	public <R> R accept(K key, Acceptor<C, R> acceptor, R nullValue) {
		C c = this.registry.get(key);
		if (c == null) {
			return nullValue;
		}
		return acceptor.accept(c);
	}
}
