package werkzeugkasten.mvncrawler;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Waiter {

	protected static final Logger LOG = LoggerFactory.getLogger(Waiter.class);

	protected ScheduledExecutorService executorService;

	protected Eater eater;

	public Waiter(Eater eater) {
		this.eater = eater;
		this.executorService = Executors.newScheduledThreadPool(20);
		this.executorService.schedule(new Runnable() {
			@Override
			public void run() {
				executorService.shutdown();
			}
		}, 10, TimeUnit.HOURS);
	}

	public void serv(final URL url, final CrawlerContext parent) {
		if (executorService.isShutdown() == false
				&& executorService.isTerminated() == false) {
			executorService.schedule(new Runnable() {
				@Override
				public void run() {
					try {
						CrawlerContext c = new CrawlerContext(parent);
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
			executorService.awaitTermination(10, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
