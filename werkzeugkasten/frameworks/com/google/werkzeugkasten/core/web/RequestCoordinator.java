package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Initializable.Initialize;

public interface RequestCoordinator<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES, Void>, CTL, A extends Action<APP, REQ, RES, CTX>> {

	@Initialize
	void initialize(CTL controller);

	A coordinate(CTX context);
}
