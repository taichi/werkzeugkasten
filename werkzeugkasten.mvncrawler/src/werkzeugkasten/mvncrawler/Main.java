package werkzeugkasten.mvncrawler;

public class Main {

	public static void main(String[] args) {
		new Main().execute();
	}

	public Main() {
	}

	public void execute() {
		String topUrl = "http://repo1.maven.org/maven2/velocity/";
		Waiter waiter = new Waiter();
		try {
			waiter.begin();
			CrawlerContext c = new CrawlerContext(waiter);
			c.crawlmore(topUrl);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			waiter.finish();
		}
	}

}
