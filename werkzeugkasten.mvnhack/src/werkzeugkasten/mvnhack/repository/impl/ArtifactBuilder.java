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

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;

public class ArtifactBuilder {

	public Artifact build(Context context, InputStream pom) {
		try {
			Document doc = toDocument(context, pom);
			XPath path = XPathFactory.newInstance().newXPath();

			Element elem = doc.getDocumentElement();
			Map<String, String> replacer = new HashMap<String, String>();

			Artifact parent = resolveParent(context, path, elem, replacer);

			DefaultArtifact a = createArtifact(path, elem, replacer, parent);

			addManagedDependencies(path, elem, replacer, a);

			addDependencies(path, elem, replacer, a);

			if (validate(a)) {
				return a;
			}
		} catch (Exception e) {
			Constants.LOG.log(Level.WARNING, e.getMessage(), e);
		}
		return null;
	}

	protected Document toDocument(Context context, InputStream pom)
			throws ParserConfigurationException, SAXException, IOException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource src = new InputSource(new BufferedInputStream(pom));
			src.setEncoding("UTF-8");
			return builder.parse(src);
		} finally {
			context.close(pom);
		}
	}

	protected Artifact resolveParent(Context context, XPath path, Element elem,
			Map<String, String> replacer) throws XPathExpressionException {
		String groupId = StringUtil.toString(path.evaluate("parent/groupId",
				elem));
		String artifactId = StringUtil.toString(path.evaluate(
				"parent/artifactId", elem));
		String version = StringUtil.toString(path.evaluate("parent/version",
				elem));
		if (validate(groupId, artifactId, version)) {
			Artifact parent = context.resolve(groupId, artifactId, version);
			if (parent != null) {
				putContextValues(replacer, parent, "parent");
			}
			return parent;
		}
		return null;
	}

	protected void putContextValues(Map<String, String> m, Artifact a,
			String prefix) {
		m.put(prefix + ".groupId", a.getGroupId());
		m.put(prefix + ".artifactId", a.getArtifactId());
		m.put(prefix + ".version", a.getVersion());
	}

	protected DefaultArtifact createArtifact(XPath path, Element elem,
			Map<String, String> replacer, Artifact parent)
			throws XPathExpressionException {
		DefaultArtifact a = new DefaultArtifact(parent);
		setValues(path, a, elem, replacer);
		a.setType(path.evaluate("packaging", elem));
		putContextValues(replacer, a, "project");
		return a;
	}

	protected void setValues(XPath path, DefaultArtifact a, Node n,
			Map<String, String> replacer) throws XPathExpressionException {
		a.setGroupId(StringUtil.replace(path.evaluate("groupId", n), replacer));
		a.setArtifactId(StringUtil.replace(path.evaluate("artifactId", n),
				replacer));
		a.setVersion(StringUtil.replace(path.evaluate("version", n), replacer));
	}

	protected boolean isNotOptional(String optional) {
		return StringUtil.isEmpty(optional)
				|| Boolean.parseBoolean(optional) == false;
	}

	protected boolean isNotTest(String scope) {
		return StringUtil.isEmpty(scope)
				|| "test".equalsIgnoreCase(scope) == false;
	}

	protected boolean isValidNode(XPath path, Node n)
			throws XPathExpressionException {
		return isNotOptional(path.evaluate("optional", n))
				&& isNotTest(path.evaluate("scope", n));
	}

	protected void addManagedDependencies(XPath path, Element e,
			Map<String, String> replacer, DefaultArtifact a)
			throws XPathExpressionException {
		NodeList list = (NodeList) path.evaluate(
				"dependencyManagement/dependencies/dependency", e,
				XPathConstants.NODESET);
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			if (isValidNode(path, n)) {
				DefaultArtifact d = new DefaultArtifact();
				setValues(path, d, n, replacer);
				if (validate(d)) {
					a.addManagedDependency(d);
				}
			}
		}
	}

	protected void addDependencies(XPath path, Element e,
			Map<String, String> replacer, DefaultArtifact a)
			throws XPathExpressionException {
		NodeList list = (NodeList) path.evaluate("dependencies/dependency", e,
				XPathConstants.NODESET);
		for (int i = 0; i < list.getLength(); i++) {
			Node n = list.item(i);
			if (isValidNode(path, n)) {
				DefaultArtifact d = new DefaultArtifact();
				setValues(path, d, n, replacer);
				d.setType(path.evaluate("type", n));
				if (StringUtil.isEmpty(d.getVersion())) {
					d.setVersion(a.getManagedDependency(d));
				}
				if (validate(d)) {
					a.add(d);
				}
			}
		}
	}

	protected boolean validate(Artifact a) {
		return validate(a.getGroupId(), a.getArtifactId(), a.getVersion());
	}

	protected boolean validate(String... ids) {
		for (String s : ids) {
			if (StringUtil.isEmpty(s)) {
				return false;
			}
		}
		return true;
	}

}
