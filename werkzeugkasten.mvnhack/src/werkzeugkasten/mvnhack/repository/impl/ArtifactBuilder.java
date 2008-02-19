package werkzeugkasten.mvnhack.repository.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import werkzeugkasten.mvnhack.repository.Artifact;

public class ArtifactBuilder {

	Logger logger = Logger.getLogger(ArtifactBuilder.class.getName());

	public Artifact build(InputStream pom) {
		try {
			Document doc = toDocument(pom);
			XPathFactory xf = XPathFactory.newInstance();
			XPath path = xf.newXPath();

			DefaultArtifact a = new DefaultArtifact();
			Element elem = doc.getDocumentElement();
			setValue(path, a, elem);
			a.setType(path.evaluate("packaging", elem));

			NodeList list = (NodeList) path.evaluate(
					"project/dependencies/dependency", doc,
					XPathConstants.NODESET);
			for (int i = 0; i < list.getLength(); i++) {
				Node n = list.item(i);
				DefaultDependency d = new DefaultDependency();
				setValue(path, d, n);
				a.setType(path.evaluate("type", n));
				a.add(d);
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