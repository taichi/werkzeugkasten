package werkzeugkasten.core.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import werkzeugkasten.core.Chain;
import werkzeugkasten.core.ChainContext;
import werkzeugkasten.core.OrderedChain;

public class DefaultOrderedChain<R, CTX extends ChainContext<R>> implements
		OrderedChain<R, CTX> {

	protected Queue<Chain<R, CTX>> chains = new LinkedList<Chain<R, CTX>>();

	public void add(Chain<R, CTX> c) {
		this.chains.offer(c);
	}

	public R execute(CTX parameter) {
		R result = null;
		for (Chain<R, CTX> c : this.chains) {
			result = c.execute(parameter);
		}
		return result;
	}

	public Iterator<Chain<R, CTX>> iterator() {
		return this.chains.iterator();
	}

}
