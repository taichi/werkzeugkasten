package org.handwerkszeug.container;

public interface Container<C extends Nameable, K> extends Nameable {

	void walk(Acceptor<C, Boolean> walker);

	<R> R accept(K key, Acceptor<C, R> acceptor, R nullValue);
}
