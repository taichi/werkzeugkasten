package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Executable;

public interface Renderer<APP, REQ, RES> extends
		Executable<Void, WebContext<APP, REQ, RES>> {

}
