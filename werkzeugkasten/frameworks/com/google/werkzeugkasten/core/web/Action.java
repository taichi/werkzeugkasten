package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten._;

public interface Action<APP, REQ, RES> {

	<CTX extends WebContext<APP, REQ, RES, _>> Renderer<APP, REQ, RES> execute(
			CTX context);

	<CTX extends WebContext<APP, REQ, RES, _>> boolean match(CTX context);
}
