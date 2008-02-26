package werkzeugkasten.mvncrawler;

import werkzeugkasten.common.util.UrlUtil;

public class CrawlerContext {
	protected int depth = 0;

	protected Waiter waiter;

	public CrawlerContext(Waiter waiter) {
		this.waiter = waiter;
	}

	public CrawlerContext(CrawlerContext parent) {
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