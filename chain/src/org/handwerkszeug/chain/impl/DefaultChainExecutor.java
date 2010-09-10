package org.handwerkszeug.chain.impl;

import java.util.ArrayList;
import java.util.List;

import org.handwerkszeug.chain.Chain;
import org.handwerkszeug.chain.ChainExecutor;
import org.handwerkszeug.chain.ChainResult;

public class DefaultChainExecutor<CTX, R extends ChainResult> implements
		ChainExecutor<CTX, R> {

	protected List<Chain<CTX>> chains = new ArrayList<Chain<CTX>>();

	@Override
	public ChainResult execute(CTX context) {
		ChainResult r = null;
		for (Chain<CTX> c : chains) {
			r = c.execute(context);
			if (r.hasNext() == false) {
				break;
			}
		}
		return r;
	}

}
