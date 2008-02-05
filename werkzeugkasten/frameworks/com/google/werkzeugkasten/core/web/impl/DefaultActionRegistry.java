package com.google.werkzeugkasten.core.web.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.werkzeugkasten.core.web.Action;
import com.google.werkzeugkasten.core.web.ActionRegistry;
import com.google.werkzeugkasten.core.web.WebContext;

public class DefaultActionRegistry<APP, REQ, RES, ACT extends Action<APP, REQ, RES>>
		implements ActionRegistry<APP, REQ, RES, ACT> {

	protected List<ACT> actions = new ArrayList<ACT>();

	public void add(ACT... actions) {
		for (ACT a : actions) {
			this.actions.add(a);
		}
	}

	public void remove(ACT... actions) {
		for (ACT a : actions) {
			this.actions.add(a);
		}
	}

	public <CTX extends WebContext<APP, REQ, RES>> ACT find(CTX context) {
		for (ACT a : this.actions) {
			if (a.match(context)) {
				return a;
			}
		}
		return null;
	}

}
