package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Executable;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

public interface Action<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES>>
		extends Executable<Renderer<APP, REQ, RES, CTX>, CTX> {

	@Initialize
	void initialize(String pattern, RequestMethod[] methods);

	boolean match(CTX context);
}
