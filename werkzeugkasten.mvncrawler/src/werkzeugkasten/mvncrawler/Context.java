package werkzeugkasten.mvncrawler;

import werkzeugkasten.common.util.UrlUtil;

public class Context {
	protected int depth = 0;

	protected Waiter waiter;

	public Context(Waiter waiter) {
		this.waiter = waiter;
	}

	public Context(Context parent) {
		if (parent != null) {
			this.depth = parent.getDepth() + 1;
			this.waiter = parent.waiter;
		}
	}

	public int getDepth() {
		return depth;
	}

	public void crawlmore(final String url) {
		if (getDepth() < 5) {
			this.waiter.serv(UrlUtil.toURL(url), this);
		}
	}

	public void eat(final String pom) {
		this.waiter.serv(UrlUtil.toURL(pom));
	}
}