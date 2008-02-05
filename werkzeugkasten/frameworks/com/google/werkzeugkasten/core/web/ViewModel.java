package com.google.werkzeugkasten.core.web;

public interface ViewModel<APP, REQ, RES> {

	<CTX extends WebContext<APP, REQ, RES>> void validate(CTX context);
}
