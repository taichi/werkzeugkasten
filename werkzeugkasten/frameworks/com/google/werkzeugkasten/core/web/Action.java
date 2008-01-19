package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Chain;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

public interface Action<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES, Void>>
		extends
		Chain<Renderer<APP, REQ, RES, CTX>, WebContext<APP, REQ, RES, Renderer<APP, REQ, RES, CTX>>> {

	@Initialize
	void initialize(String pattern, RequestMethod[] methods);
}
