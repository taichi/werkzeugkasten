package com.google.werkzeugkasten.core.web;

import java.io.File;
import java.io.OutputStream;

public interface Finder<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES, Void>> {

	Renderer<APP, REQ, RES, CTX> json(Object obj);

	Renderer<APP, REQ, RES, CTX> xml();

	Renderer<APP, REQ, RES, CTX> file(File file);

	Renderer<APP, REQ, RES, CTX> forward(String path);

	Renderer<APP, REQ, RES, CTX> redirect(String path);

	Renderer<APP, REQ, RES, CTX> stream(OutputStream out);
}
