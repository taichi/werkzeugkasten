package com.google.werkzeugkasten.core.web;

public interface ScopeProvider<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES>> {

	Object get(CTX context, String key);

	interface Writable<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES>>
			extends ScopeProvider<APP, REQ, RES, CTX> {
		void set(CTX context, String key, Object value);
	}
}
