package werkzeugkasten.ecf.provider.twitter4j;

import junit.framework.TestCase;
import twitter4j.Status;
import twitter4j.Twitter;

public class SendTest extends TestCase {

	static {
		System.setProperty("http.proxyHost", "sg-sk24-1.isid.co.jp");
		System.setProperty("http.proxyPort", "8080");
	}

	Twitter twitter;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.twitter = new Twitter("ryushi", "azupon");
		this.twitter.setSource("Taichi Special");
		this.twitter.setRequestHeader("X-Twitter-Client",
				"werkzeugkasten.ecf.provider.twitter4j");
		this.twitter.setRequestHeader("X-Twitter-Client-Version", "0.0.1");
		this.twitter.setRequestHeader("X-Twitter-Client-URL", "");
	}

	public void testSend() throws Exception {
		Status st = twitter.update("のひぇぇぇぇ");
		System.out.printf("Text : %s\n", st.getText());
	}

	public void testGetTimeline() throws Exception {
		for (Status s : twitter.getFriendsTimeline()) {
			System.out.printf("%s : %s [%s]\n", s.getUser().getName(), s
					.getText(), s.getSource());
		}
	}
}
