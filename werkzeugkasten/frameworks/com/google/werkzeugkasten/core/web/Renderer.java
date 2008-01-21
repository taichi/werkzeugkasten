package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.Executable;

public interface Renderer<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES>>
		extends Executable<_, CTX> {

}
