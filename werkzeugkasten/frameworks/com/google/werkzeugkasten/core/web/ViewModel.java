package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten._;

public interface ViewModel<APP, REQ, RES> {

	<CTX extends WebContext<APP, REQ, RES, _>> void validate(CTX context);
}
