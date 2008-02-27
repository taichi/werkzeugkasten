package werkzeugkasten.mvncrawler;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Waiter {

	protected ScheduledExecutorService executorService;

	protected Eater eater;

	public Waiter(Eater eater) {
		this.eater = eater;
		this.executorService = Executors.newScheduledThreadPool(5);
		this.executorService.schedule(new Runnable() {
			@Override
			public void run() {
				executorService.shutdown();
			}
		}, 3, TimeUnit.MINUTES);
	}

	public void serv(final URL url, final CrawlerContext parent) {
		executorService.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					CrawlerContext c = new CrawlerContext(parent);
					eater.parse(c, url);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 0, TimeUnit.SECONDS);
	}

	public void serv(final URL pom) {
		executorService.schedule(new Runnable() {
			@Override
			public void run() {
				eater.eat(pom);
			}
		}, 10, TimeUnit.SECONDS);
	}

	public void dispose() {
		try {
			executorService.awaitTermination(4, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
