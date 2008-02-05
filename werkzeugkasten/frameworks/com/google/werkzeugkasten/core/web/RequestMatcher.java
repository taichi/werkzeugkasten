package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.Chain;

public interface RequestMatcher<APP, REQ, RES, CTX extends WebContext<APP, REQ, RES, Boolean>>
		extends Chain<Boolean, CTX> {

}
