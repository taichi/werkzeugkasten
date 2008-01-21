package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Executable;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

public interface Action<APP, REQ, RES> extends
		Executable<Renderer<APP, REQ, RES>, WebContext<APP, REQ, RES>> {

	@Initialize
	void initialize(String pattern, RequestMethod[] methods);
}
