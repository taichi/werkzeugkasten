package com.google.werkzeugkasten.core.web;


public interface Action<APP, REQ, RES> {

	<CTX extends WebContext<APP, REQ, RES>> Renderer<APP, REQ, RES> execute(
			CTX context);

	<CTX extends WebContext<APP, REQ, RES>> boolean match(CTX context);
}
