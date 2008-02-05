package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Initializable.Initialize;

public interface Action<APP, REQ, RES> {

	@Initialize
	void initialize(String pattern, RequestMethod... methods);

	<CTX extends WebContext<APP, REQ, RES>> Renderer<APP, REQ, RES, CTX> execute(
			CTX context);

	<CTX extends WebContext<APP, REQ, RES>> boolean match(CTX context);
}
