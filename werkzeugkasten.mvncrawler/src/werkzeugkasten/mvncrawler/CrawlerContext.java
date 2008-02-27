package werkzeugkasten.mvncrawler;

import werkzeugkasten.common.util.UrlUtil;

public class CrawlerContext {

	protected String baseUrl;

	protected int depth = 0;

	protected Waiter waiter;

	public CrawlerContext(String url, Waiter waiter) {
		this.baseUrl = url;
		this.waiter = waiter;
	}

	public CrawlerContext(CrawlerContext parent) {
		if (parent != null) {
			this.baseUrl = parent.baseUrl;
			this.depth = parent.depth + 1;
			this.waiter = parent.waiter;
		}
	}

	public void crawlmore(final String url) {
		if (depth < 10 && url.startsWith(baseUrl)) {
			this.waiter.serv(UrlUtil.toURL(url), this);
		}
	}

	public void eat(final String pom) {
		this.waiter.serv(UrlUtil.toURL(pom));
	}
}