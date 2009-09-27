package org.handwerkszeug.container;

public interface Acceptor<C, R> {
	R accept(C content);
}