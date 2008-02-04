package com.google.werkzeugkasten.core.web;

public interface UriMatcher<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES>> {

	boolean match(CTX context);
}
