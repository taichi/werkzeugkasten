package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten._;

public interface ActionRegistry<APP, REQ, RES, ACT extends Action<APP, REQ, RES>> {

	void add(ACT... actions);

	void remove(ACT... actions);

	<CTX extends WebContext<APP, REQ, RES, _>> ACT find(CTX context);
}
