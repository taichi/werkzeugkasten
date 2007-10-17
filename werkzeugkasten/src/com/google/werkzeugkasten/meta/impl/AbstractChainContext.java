package com.google.werkzeugkasten.meta.impl;

import java.util.LinkedList;
import java.util.Queue;

import com.google.werkzeugkasten.meta.Chain;
import com.google.werkzeugkasten.meta.ChainContext;

public abstract class AbstractChainContext<R, CTX extends ChainContext<R>>
		implements ChainContext<R> {

	protected Queue<Chain<R, CTX>> chains = new LinkedList<Chain<R, CTX>>();

	@SuppressWarnings("unchecked")
	public R execute() {
		Chain<R, CTX> c = this.chains.poll();
		if (c != null) {
			return c.execute((CTX) this);
		} else {
			// FIXME : errormsgs
			throw new IllegalStateException("Empty Chain");
		}
	}

	public void add(Chain<R, CTX> c) {
		this.chains.offer(c);
	}
}
