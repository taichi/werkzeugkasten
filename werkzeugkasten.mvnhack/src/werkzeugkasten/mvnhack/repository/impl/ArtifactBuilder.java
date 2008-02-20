package werkzeugkasten.mvnhack.repository.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

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

	public Artifact build(InputStream pom) {
		try {
			Document doc = toDocument(pom);
			XPath path = XPathFactory.newInstance().newXPath();
			Element elem = doc.getDocumentElement();

			Map<String, String> parent = new HashMap<String, String>();
			put(parent, "parent.groupId", path.evaluate("parent/groupId", elem));
			put(parent, "parent.artifactId", path.evaluate("parent/artifactId",
					elem));
			put(parent, "parent.version", path.evaluate("parent/version", elem));

			DefaultArtifact a = new DefaultArtifact();
			setValue(path, a, elem, parent);
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
					setValue(path, d, n, parent);
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
			Constants.LOG.log(Level.WARNING, e.getMessage(), e);
		} finally {
			StreamUtil.close(pom);
		}
		return null;
	}

	protected void put(Map<String, String> m, String key, String value) {
		if (StringUtil.isEmpty(value) == false) {
			m.put(key, value);
		}
	}

	protected boolean isNotOptional(String optional) {
		return StringUtil.isEmpty(optional)
				|| Boolean.parseBoolean(optional) == false;
	}

	protected boolean isNotTest(String scope) {
		return StringUtil.isEmpty(scope)
				|| "test".equalsIgnoreCase(scope) == false;
	}

	protected void setValue(XPath path, DefaultArtifact a, Node elem,
			Map<String, String> parent) throws XPathExpressionException {
		a.setGroupId(toValue(path, elem, "groupId", parent));
		a.setArtifactId(path.evaluate("artifactId", elem));
		a.setVersion(toValue(path, elem, "version", parent));
	}

	protected String toValue(XPath path, Node elem, String name,
			Map<String, String> parent) throws XPathExpressionException {
		String v = path.evaluate(name, elem);
		if (StringUtil.isEmpty(v)) {
			v = parent.get("parent." + name);
		} else {
			v = StringUtil.replace(v, parent);
		}
		return v;
	}

	protected Document toDocument(InputStream pom)
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
