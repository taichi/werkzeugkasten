package com.google.werkzeugkasten.core.web;

import com.google.werkzeugkasten._;
import com.google.werkzeugkasten.meta.ChainContext;

public interface WebContext<APP, REQ, RES> extends ChainContext<_> {

	APP getApplication();

	REQ getRequest();

	RES getResponse();

	ViewModel<APP, REQ, RES> getModel();

	Action<APP, REQ, RES> getAction();

}
