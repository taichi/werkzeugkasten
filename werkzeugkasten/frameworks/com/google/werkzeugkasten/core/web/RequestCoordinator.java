package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Executable;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

public interface RequestCoordinator<APP, REQ, RES, CTL> extends
		Executable<Action<APP, REQ, RES>, WebContext<APP, REQ, RES>> {

	@Initialize
	void initialize(CTL controller);

}
