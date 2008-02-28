package werkzeugkasten.mvncrawler;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.common.util.StringUtil;

public class Waiter {

	protected static final Logger LOG = LoggerFactory.getLogger(Waiter.class);

	protected ScheduledExecutorService executorService;

	protected Eater eater;

	public Waiter(Eater eater) {
		this.eater = eater;
		int thread = 20;
		String t = System.getProperty("Waiter.Thread");
		if (StringUtil.isEmpty(t)) {
			thread = Integer.parseInt(t);
		}
		this.executorService = Executors.newScheduledThreadPool(thread);
		this.executorService.schedule(new Runnable() {
			@Override
			public void run() {
				executorService.shutdown();
			}
		}, 4, TimeUnit.HOURS);
	}

	public void serv(final URL url, final CrawlerContext parent) {
		if (executorService.isShutdown() == false
				&& executorService.isTerminated() == false) {
			executorService.schedule(new Runnable() {
				@Override
				public void run() {
					try {
						CrawlerContext c = new CrawlerContext(parent);
						LOG.info(url.toExternalForm());
						eater.parse(c, url);
					} catch (IOException e) {
						LOG.error(e.getMessage(), e);
					}
				}
			}, 0, TimeUnit.SECONDS);
		}
	}

	public void serv(final URL pom) {
		if (executorService.isShutdown() == false
				&& executorService.isTerminated() == false) {
			executorService.schedule(new Runnable() {
				@Override
				public void run() {
					eater.eat(pom);
				}
			}, 10, TimeUnit.SECONDS);
		}
	}

	public void dispose() {
		try {
			executorService.awaitTermination(4, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
