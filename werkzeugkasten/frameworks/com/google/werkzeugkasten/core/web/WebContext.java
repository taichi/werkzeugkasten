package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.ChainContext;

public interface WebContext<APP, REQ, RES, R> extends ChainContext<R> {

	APP getApplication();

	REQ getRequest();

	RES getResponse();
}
