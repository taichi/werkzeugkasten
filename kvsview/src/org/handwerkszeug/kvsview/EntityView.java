package org.handwerkszeug.kvsview;

import voldemort.versioning.Versioned;

public interface EntityView<V> {

	@NotNull
	Iterable<Class<?>> getAccessibleEntities();

	@NotNull
	Iterable<Versioned<V>> getAllEntities(@NotNull Class<V> entity,
			@NotNull Filter<V> filter);

	@NotNull
	Iterable<V> getAllEntityValues(@NotNull Class<V> entity,
			@NotNull Filter<V> filter);
}