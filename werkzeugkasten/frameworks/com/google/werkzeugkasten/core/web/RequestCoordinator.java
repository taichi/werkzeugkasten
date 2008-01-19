package com.google.werkzeugkasten.core.web;

public interface RequestCoordinator<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES, Void>, A extends Action<APP, REQ, RES, CTX>> {

	A coordinate(CTX context);
}
