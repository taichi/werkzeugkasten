package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten.meta.ChainContext;

public interface WebContext<APP, REQ, RES> extends ChainContext<Void> {

	APP getApplication();

	REQ getRequest();

	RES getResponse();
}
