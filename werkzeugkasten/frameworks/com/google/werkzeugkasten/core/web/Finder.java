package com.google.werkzeugkasten.core.web;

import java.io.File;
import java.io.OutputStream;

public interface Finder<APP, REQ, RES> {

	Renderer<APP, REQ, RES> json(Object obj);

	Renderer<APP, REQ, RES> xml();

	Renderer<APP, REQ, RES> file(File file);

	Renderer<APP, REQ, RES> forward(String path);

	Renderer<APP, REQ, RES> redirect(String path);

	Renderer<APP, REQ, RES> stream(OutputStream out);
}
