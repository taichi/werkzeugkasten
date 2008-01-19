package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Chain;

public interface Renderer<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES, Void>>
		extends Chain<Void, CTX> {

}
