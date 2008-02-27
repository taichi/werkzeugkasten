package werkzeugkasten.mvncrawler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jp.aonir.fuzzyxml.FuzzyXMLDocument;
import jp.aonir.fuzzyxml.FuzzyXMLElement;
import jp.aonir.fuzzyxml.FuzzyXMLNode;
import jp.aonir.fuzzyxml.FuzzyXMLParser;
import jp.aonir.fuzzyxml.XPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.ArtifactBuilder;
import werkzeugkasten.mvnhack.repository.Context;

public class Eater {

	protected static final Logger LOG = LoggerFactory.getLogger(Eater.class);

	protected static final Pattern ignore = Pattern.compile(".*[\\?#:].*");

	protected static final Pattern files = Pattern
			.compile(".*\\.(xml|md5|sha1|jar|zip)");

	protected Context context;
	protected ArtifactBuilder builder;
	protected CrawlerDao dao;

	public Eater(Context context, ArtifactBuilder builder, CrawlerDao dao) {
		this.context = context;
		this.builder = builder;
		this.dao = dao;
	}

	public void parse(CrawlerContext c, URL url) throws IOException {
		FuzzyXMLParser parser = new FuzzyXMLParser(true);
		FuzzyXMLDocument doc = parser.parse(new BufferedInputStream(UrlUtil
				.open(url)));
		FuzzyXMLElement elem = doc.getDocumentElement();
		FuzzyXMLNode[] nodes = XPath.selectNodes(elem, "//a");
		List<String> urls = new ArrayList<String>(nodes.length);
		boolean[] hasPom = { false };
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] instanceof FuzzyXMLElement) {
				FuzzyXMLElement e = (FuzzyXMLElement) nodes[i];
				processHref(c, url, e.getAttributeValue("href"), urls, hasPom);
			}
		}
		if (hasPom[0] == false) {
			for (String s : urls) {
				c.crawlmore(s);
			}
		}
	}

	protected void processHref(CrawlerContext c, URL current, String href,
			List<String> list, boolean[] hasPom) {
		if (StringUtil.isEmpty(href)) {
			return;
		}
		if (ignore.matcher(href).matches() || files.matcher(href).matches()) {
			return;
		}

		String ef = current.toExternalForm();
		StringBuilder stb = new StringBuilder();
		if (href.startsWith("/")) {
			stb.append(ef.substring(0, ef.indexOf(current.getPath())));
		} else {
			stb.append(ef);
			if (ef.endsWith("/") == false) {
				stb.append("/");
			}
		}
		stb.append(href);
		String newUrl = stb.toString();
		if (newUrl.endsWith(".pom")) {
			c.eat(newUrl);
			hasPom[0] = true;
		} else {
			String[] oldary = ef.split("/");
			String[] newary = newUrl.split("/");
			if (oldary.length < newary.length) {
				list.add(newUrl);
			}
		}
	}

	public void eat(URL pom) {
		LOG.info("Read from " + pom);
		Artifact a = this.builder.build(this.context, UrlUtil.open(pom));
		if ("pom".equalsIgnoreCase(a.getType()) == false) {
			this.dao.entry(a);
		}
	}
}
