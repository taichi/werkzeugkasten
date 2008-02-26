package werkzeugkasten.mvncrawler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

import jp.aonir.fuzzyxml.FuzzyXMLDocument;
import jp.aonir.fuzzyxml.FuzzyXMLElement;
import jp.aonir.fuzzyxml.FuzzyXMLNode;
import jp.aonir.fuzzyxml.FuzzyXMLParser;
import jp.aonir.fuzzyxml.XPath;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.UrlUtil;

public class Main {

	public static void main(String[] args) {
		new Main().execute();
	}

	Main() {
	}

	public void execute() {
		String topUrl = "http://repo1.maven.org/maven2/velocity/";
		Context c = new Context(UrlUtil.toURL(topUrl));
		dive(c);
	}

	protected void dive(Context c) {
		try {
			parse(c, c.getCurrent());

			if (c.getDepth() < 5) {
				for (String links : c.urls) {
					Context newone = new Context(UrlUtil.toURL(links), c);
					dive(newone);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void parse(Context c, URL url) throws IOException {
		FuzzyXMLParser parser = new FuzzyXMLParser(true);
		FuzzyXMLDocument doc = parser.parse(new BufferedInputStream(UrlUtil
				.open(url)));
		FuzzyXMLElement elem = doc.getDocumentElement();
		FuzzyXMLNode[] nodes = XPath.selectNodes(elem, "//a");
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof FuzzyXMLElement) {
				FuzzyXMLElement e = (FuzzyXMLElement) nodes[i];
				processHref(c, e.getAttributeValue("href"));
			}
		}
	}

	static final Pattern ignore = Pattern.compile(".*[\\?#:].*");

	static final Pattern files = Pattern.compile(".*\\.(xml|md5|sha1|jar|zip)");

	private void processHref(Context c, String href) {
		if (StringUtil.isEmpty(href)) {
			return;
		}
		if (ignore.matcher(href).matches() || files.matcher(href).matches()) {
			return;
		}

		String ef = c.getCurrent().toExternalForm();
		StringBuilder stb = new StringBuilder();
		if (href.startsWith("/")) {
			stb.append(ef.substring(0, ef.indexOf(c.getCurrent().getPath())));
		} else {
			stb.append(ef);
			if (ef.endsWith("/") == false) {
				stb.append("/");
			}
		}
		stb.append(href);
		String newUrl = stb.toString();
		if (newUrl.endsWith(".pom")) {
			queue(newUrl);
		} else {
			String[] oldary = ef.split("/");
			String[] newary = newUrl.split("/");
			if (oldary.length < newary.length) {
				c.urls.add(newUrl);
			}
		}
	}

	protected void queue(String url) {
		System.out.println("***** " + url);
	}

	class Context {
		int depth = 0;
		URL current;
		Set<String> urls = new LinkedHashSet<String>();

		Context(URL url) {
			this.current = url;
		}

		Context(URL url, Context parent) {
			this.current = url;
			this.depth = parent.getDepth() + 1;
		}

		public URL getCurrent() {
			return current;
		}

		public int getDepth() {
			return depth;
		}
	}
}
