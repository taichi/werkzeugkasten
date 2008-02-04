package com.google.werkzeugkasten.core.web;

public interface ActionRegistry<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES>, ACT extends Action<APP, REQ, RES, CTX>> {

	void add(ACT... actions);

	void remove(ACT... actions);

	ACT find(CTX context);
}
