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
		String topUrl = "http://repo1.maven.org/maven2/";
		URL url = UrlUtil.toURL(topUrl);
		dive(url, new Context());

	}

	protected void dive(URL url, Context parent) {
		try {
			Context c = new Context(parent);
			c.current = url;

			parse(url, c);

			if (c.depth < 2) {
				for (String links : c.urls) {
					dive(UrlUtil.toURL(links), c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void parse(URL url, Context c) throws IOException {
		FuzzyXMLParser parser = new FuzzyXMLParser(true);
		FuzzyXMLDocument doc = parser.parse(new BufferedInputStream(UrlUtil
				.open(url)));
		FuzzyXMLElement elem = doc.getDocumentElement();
		FuzzyXMLNode[] nodes = XPath.selectNodes(elem, "//a");
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof FuzzyXMLElement) {
				FuzzyXMLElement element = (FuzzyXMLElement) nodes[i];
				processHref(c, element.getAttributeValue("href"));
			}
		}
	}

	static final Pattern ignore = Pattern.compile(".*[\\?#:].*");

	private void processHref(Context c, String href) {
		if (StringUtil.isEmpty(href)) {
			return;
		}
		if (ignore.matcher(href).matches()) {
			return;
		}

		String ef = c.current.toExternalForm();
		if (ef.endsWith("\\.pom")) {
			queue(ef);
		} else {
			StringBuilder stb = new StringBuilder();
			if (href.startsWith("/")) {
				stb.append(ef.substring(0, ef.indexOf(c.current.getPath())));
			} else {
				stb.append(ef);
				if (ef.endsWith("/") == false) {
					stb.append("/");
				}
			}
			stb.append(href);
			String newUrl = stb.toString();
			String[] oldary = ef.split("/");
			String[] newary = newUrl.split("/");
			if (oldary.length < newary.length) {
				c.urls.add(newUrl);
			}
		}
	}

	protected void queue(String ef) {

	}

	class Context {
		int depth = 0;
		URL current;
		Set<String> urls = new LinkedHashSet<String>();

		Context() {
		}

		Context(Context parent) {
			this.depth = parent.depth + 1;
		}
	}
}
