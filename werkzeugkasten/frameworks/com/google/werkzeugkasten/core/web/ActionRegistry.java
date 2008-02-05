package com.google.werkzeugkasten.core.web;

public interface ActionRegistry<APP, REQ, RES, ACT extends Action<APP, REQ, RES>> {

	void add(ACT... actions);

	void remove(ACT... actions);

	<CTX extends WebContext<APP, REQ, RES>> ACT find(CTX context);
}
