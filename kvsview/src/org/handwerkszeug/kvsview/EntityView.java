package org.handwerkszeug.kvsview;

import voldemort.versioning.Versioned;

public interface EntityView {

	@NotNull
	Iterable<Class<?>> getAccessibleEntities();

	@NotNull
	<V> Iterable<Versioned<V>> getAllEntities(@NotNull Class<V> entity,
			@NotNull Filter<V> filter);

	@NotNull
	<V> Iterable<V> getAllEntityValues(@NotNull Class<V> entity,
			@NotNull Filter<V> filter);
}