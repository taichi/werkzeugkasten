package werkzeugkasten.mvnhack.repository.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

	protected static Set<String> legalScopes = new HashSet<String>();

	public Artifact build(InputStream pom) {
		try {
			Document doc = toDocument(pom);
			XPath path = XPathFactory.newInstance().newXPath();

			Element elem = doc.getDocumentElement();
			Map<String, String> context = new HashMap<String, String>();

			DefaultArtifact a = createArtifact(path, elem, context);

			addDependencies(path, elem, context, a);

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

	protected Document toDocument(InputStream pom)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource src = new InputSource(new BufferedInputStream(pom));
		src.setEncoding("UTF-8");
		return builder.parse(src);
	}

	protected DefaultParentArtifact createParent(XPath path, Element elem,
			Map<String, String> context) throws XPathExpressionException {
		DefaultParentArtifact parent = null;
		Node parentNode = (Node) path.evaluate("parent", elem,
				XPathConstants.NODE);
		if (parentNode != null) {
			parent = new DefaultParentArtifact();
			setValues(path, parent, parentNode, context);
			putContextValues(context, parent, "parent");
		}
		return parent;
	}

	protected void putContextValues(Map<String, String> context,
			DefaultArtifact a, String prefix) {
		context.put(prefix + ".groupId", a.getGroupId());
		context.put(prefix + ".artifactId", a.getArtifactId());
		context.put(prefix + ".version", a.getVersion());
	}

	protected DefaultArtifact createArtifact(XPath path, Element elem,
			Map<String, String> context) throws XPathExpressionException {
		DefaultArtifact a = new DefaultArtifact();
		a.setParent(createParent(path, elem, context));
		setValues(path, a, elem, context);
		a.setType(path.evaluate("packaging", elem));
		putContextValues(context, a, "project");
		return a;
	}

	private void setValues(XPath path, DefaultArtifact a, Node elem,
			Map<String, String> context) throws XPathExpressionException {
		a.setGroupId(StringUtil
				.replace(path.evaluate("groupId", elem), context));
		a.setArtifactId(StringUtil.replace(path.evaluate("artifactId", elem),
				context));
		a.setVersion(StringUtil
				.replace(path.evaluate("version", elem), context));
	}

	protected boolean isNotOptional(String optional) {
		return StringUtil.isEmpty(optional)
				|| Boolean.parseBoolean(optional) == false;
	}

	protected boolean isNotTest(String scope) {
		return StringUtil.isEmpty(scope)
				|| "test".equalsIgnoreCase(scope) == false;
	}

	protected void addDependencies(XPath path, Element elem,
			Map<String, String> context, DefaultArtifact a)
			throws XPathExpressionException {
		NodeList list = (NodeList) path.evaluate("dependencies/dependency",
				elem, XPathConstants.NODESET);
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			String optional = path.evaluate("optional", n);
			String scope = path.evaluate("scope", n);
			if (isNotOptional(optional) && isNotTest(scope)) {
				DefaultDependency d = new DefaultDependency();
				setValues(path, d, n, context);
				a.setType(path.evaluate("type", n));
				if (validate(d)) {
					a.add(d);
				}
			}
		}
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
