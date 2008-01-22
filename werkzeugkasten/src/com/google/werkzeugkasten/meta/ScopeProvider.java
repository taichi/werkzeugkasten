package com.google.werkzeugkasten.meta;


public interface ScopeProvider<R, CTX extends ChainContext<R>> {

	Object get(CTX context, String key);

	interface Writable<R, CTX extends ChainContext<R>> extends
			ScopeProvider<R, CTX> {
		void set(CTX context, String key, Object value);
	}
}
