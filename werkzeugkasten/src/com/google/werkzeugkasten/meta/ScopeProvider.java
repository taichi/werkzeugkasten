package com.google.werkzeugkasten.meta;

public interface ScopeProvider<T, R, CTX extends ChainContext<R>> {

	T get(CTX context, String key);

	interface Writable<T, R, CTX extends ChainContext<R>> extends
			ScopeProvider<T, R, CTX> {
		void set(CTX context, String key, T value);
	}
}
