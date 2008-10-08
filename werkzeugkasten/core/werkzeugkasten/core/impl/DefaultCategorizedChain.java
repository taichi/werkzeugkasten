package werkzeugkasten.core.impl;

import java.util.HashMap;
import java.util.Map;

import werkzeugkasten.core.CategorizedChain;
import werkzeugkasten.core.Category;
import werkzeugkasten.core.Chain;
import werkzeugkasten.core.ChainContext;

public class DefaultCategorizedChain<R, CTX extends ChainContext<R>, C extends Category<R, CTX>>
		implements CategorizedChain<R, CTX, C> {

	protected Map<C, Chain<R, CTX>> map = new HashMap<C, Chain<R, CTX>>();

	public void put(C category, Chain<R, CTX> chain) {
		this.map.put(category, chain);
	}

	public R execute(CTX parameter) {
		for (C category : this.map.keySet()) {
			if (category.match(parameter)) {
				return category.execute(parameter);
			}
		}
		return null;
	}

}
