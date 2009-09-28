package org.handwerkszeug.container;

public interface Factory<T, C> {

	T create(C c);
}
