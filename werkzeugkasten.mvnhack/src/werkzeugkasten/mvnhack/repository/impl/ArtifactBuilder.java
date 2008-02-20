package werkzeugkasten.mvnhack.repository.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;

public class ArtifactBuilder {

	Logger logger = Logger.getLogger(Constants.NAME_LOGGER);

	protected static Set<String> legalScopes = new HashSet<String>();

	public Artifact build(InputStream pom) {
		try {
			Document doc = toDocument(pom);
			XPath path = XPathFactory.newInstance().newXPath();

			DefaultArtifact a = new DefaultArtifact();
			Element elem = doc.getDocumentElement();
			setValue(path, a, elem);
			a.setType(path.evaluate("packaging", elem));

			NodeList list = (NodeList) path.evaluate("dependencies/dependency",
					elem, XPathConstants.NODESET);
			// TODO dependencyManagement
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				String optional = path.evaluate("optional", n);
				String scope = path.evaluate("scope", n);
				if (isNotOptional(optional) && isNotTest(scope)) {
					DefaultDependency d = new DefaultDependency();
					setValue(path, d, n);
					a.setType(path.evaluate("type", n));
					if (validate(d)) {
						a.add(d);
					}
				}
			}

			if (validate(a)) {
				return a;
			}
			return null;
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		} finally {
			StreamUtil.close(pom);
		}
		return null;
	}

	protected boolean isNotOptional(String optional) {
		return StringUtil.isEmpty(optional)
				|| Boolean.parseBoolean(optional) == false;
	}

	protected boolean isNotTest(String scope) {
		return StringUtil.isEmpty(scope)
				|| "test".equalsIgnoreCase(scope) == false;
	}

	private void setValue(XPath path, DefaultArtifact a, Node elem)
			throws XPathExpressionException {
		a.setGroupId(path.evaluate("groupId", elem));
		a.setArtifactId(path.evaluate("artifactId", elem));
		a.setVersion(path.evaluate("version", elem));
	}

	private Document toDocument(InputStream pom)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource src = new InputSource(new BufferedInputStream(pom));
		src.setEncoding("UTF-8");

		return builder.parse(src);
	}

	protected boolean validate(Artifact a) {
		for (String s : new String[] { a.getGroupId(), a.getArtifactId(),
				a.getVersion() }) {
			if (StringUtil.isEmpty(s)) {
				return false;
			}
		}
		return true;
	}

}
