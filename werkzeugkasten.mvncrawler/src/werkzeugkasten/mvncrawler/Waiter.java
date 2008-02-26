package werkzeugkasten.mvncrawler;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Waiter {

	protected ScheduledExecutorService executorService;

	protected Eater eater = new Eater();

	public void begin() {
		this.executorService = Executors.newScheduledThreadPool(5);
	}

	public void serv(final URL url, final Context parent) {
		try {
			executorService.schedule(new Runnable() {
				@Override
				public void run() {
					try {
						Context c = new Context(parent);
						eater.parse(c, url);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}, 0, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void serv(final URL pom) {
		executorService.schedule(new Runnable() {
			@Override
			public void run() {
				eater.eat(pom);
			}
		}, 0, TimeUnit.SECONDS);
	}

	public void finish() {
		try {
			this.executorService.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
