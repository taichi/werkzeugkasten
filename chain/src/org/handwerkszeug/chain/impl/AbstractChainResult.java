package org.handwerkszeug.chain.impl;

import org.handwerkszeug.chain.ChainResult;

public abstract class AbstractChainResult implements ChainResult {

	protected boolean hasNext;

	public AbstractChainResult() {
		this(false);
	}

	public AbstractChainResult(boolean hasNext) {
		this.hasNext = hasNext;
	}

	@Override
	public boolean hasNext() {
		return this.hasNext;
	}

}
