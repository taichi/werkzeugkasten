package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Executable;

public interface UriMatcher<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES>>
		extends Executable<Boolean, CTX> {
}
