package com.google.werkzeugkasten.core.web;

public interface Action<APP, REQ, RES, WC extends WebContext<APP, REQ, RES, Void>>
		extends WebContext<APP, REQ, RES, Renderer<APP, REQ, RES, WC>> {
}
