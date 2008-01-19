package com.google.werkzeugkasten.core.web;

public interface Renderer<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES, Void>> {

	void render(CTX context);
}
