package werkzeugkasten.mvnhack.repository.impl;

import static werkzeugkasten.common.util.XMLStreamReaderUtil.parse;
import static werkzeugkasten.common.util.XMLStreamReaderUtil.put;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.XMLStreamReaderUtil.DefaultHandler;
import werkzeugkasten.common.util.XMLStreamReaderUtil.Handler;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.ArtifactBuilder;
import werkzeugkasten.mvnhack.repository.Context;

public class StAXArtifactBuilder implements ArtifactBuilder {

	@Override
	public Artifact build(Context context, InputStream pom) {
		DefaultArtifact result = new DefaultArtifact();
		Map<String, Handler> handlers = createArtifactParseHandlers(result);
		put(handlers, new DefaultHandler("project"));
		put(handlers, new Type(result, "packaging"));

		Parent parent = new Parent();
		put(handlers, parent);
		put(handlers, new Dependencies(result));
		Set<Artifact> managed = new HashSet<Artifact>();
		put(handlers, new DependencyManagement(managed));
		try {
			parse(createStreamParser(pom), handlers, "project");
			resolveParent(context, parent.getArtifact());

			Map<String, String> m = new HashMap<String, String>();
			putContextValues(m, parent.getArtifact(), "parent");
			putContextValues(m, result, "project");

			reconcile(context, result, managed, m);
			if (validate(result)) {
				return result;
			}
		} catch (Exception e) {
			Constants.LOG.log(Level.WARNING, e.getMessage(), e);
		}
		return null;
	}

	protected void putContextValues(Map<String, String> m, Artifact a,
			String prefix) {
		m.put(prefix + ".groupId", a.getGroupId());
		m.put(prefix + ".artifactId", a.getArtifactId());
		m.put(prefix + ".version", a.getVersion());
	}

	protected void resolveParent(Context context, Artifact parent) {
		if (validate(parent)) {
			context.resolve(parent.getGroupId(), parent.getArtifactId(), parent
					.getVersion());
		}
	}

	protected void reconcile(Context context, DefaultArtifact project,
			Set<Artifact> managed, Map<String, String> m) {
		reconcile(project, m);

		for (Artifact a : managed) {
			DefaultArtifact newone = new DefaultArtifact();
			reconcile(a, newone, m);
			context.addManagedDependency(newone);
		}

		List<Artifact> copy = new ArrayList<Artifact>(project.getDependencies());
		project.dependencies.clear();
		for (Artifact a : copy) {
			DefaultArtifact newone = new DefaultArtifact();
			reconcile(a, newone, m);
			if (StringUtil.isEmpty(newone.getVersion())) {
				newone.setVersion(context.getManagedDependency(a));
			}
			if (validate(newone)) {
				project.add(newone);
			}
		}
	}

	protected void reconcile(DefaultArtifact a, Map<String, String> m) {
		reconcile(a, a, m);
	}

	protected void reconcile(Artifact src, DefaultArtifact dest,
			Map<String, String> m) {
		dest.setGroupId(StringUtil.replace(src.getGroupId(), m));
		dest.setArtifactId(StringUtil.replace(src.getArtifactId(), m));
		dest.setVersion(StringUtil.replace(src.getVersion(), m));
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

	protected XMLStreamReader createStreamParser(InputStream in)
			throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,
				Boolean.FALSE);
		BufferedInputStream stream = new BufferedInputStream(in);
		XMLStreamReader reader = factory.createXMLStreamReader(stream);
		// reader = factory.createFilteredReader(reader, new StreamFilter() {
		// @Override
		// public boolean accept(XMLStreamReader reader) {
		// return reader.isStartElement() || reader.isEndElement();
		// }
		// });
		return reader;
	}

	protected Map<String, Handler> createArtifactParseHandlers(DefaultArtifact a) {
		Map<String, Handler> m = new HashMap<String, Handler>();
		put(m, new GroupId(a));
		put(m, new ArtifactId(a));
		put(m, new Version(a));
		return m;
	}

	protected class GroupId extends DefaultHandler {
		protected DefaultArtifact a;

		protected GroupId(DefaultArtifact a) {
			super("groupId");
			this.a = a;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			a.setGroupId(reader.getElementText());
		}
	}

	protected class ArtifactId extends DefaultHandler {
		protected DefaultArtifact a;

		protected ArtifactId(DefaultArtifact a) {
			super("artifactId");
			this.a = a;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			a.setArtifactId(reader.getElementText());
		}
	}

	protected class Version extends DefaultHandler {
		protected DefaultArtifact a;

		protected Version(DefaultArtifact a) {
			super("version");
			this.a = a;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			a.setVersion(reader.getElementText());
		}
	}

	protected class Type extends DefaultHandler {
		protected DefaultArtifact a;

		protected Type(DefaultArtifact a) {
			this(a, "type");
		}

		protected Type(DefaultArtifact a, String tag) {
			super(tag);
			this.a = a;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			a.setType(reader.getElementText());
		}
	}

	protected class Parent extends DefaultHandler {
		protected DefaultArtifact a;

		protected Parent() {
			super("parent");
			this.a = new DefaultArtifact();
		}

		public Artifact getArtifact() {
			return this.a;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			Map<String, Handler> m = createArtifactParseHandlers(a);
			parse(reader, m, getTagName());
		}
	}

	protected class Dependencies extends DefaultHandler {
		protected DefaultArtifact project;

		protected Dependencies(DefaultArtifact project) {
			super("dependencies");
			this.project = project;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			Map<String, Handler> m = new HashMap<String, Handler>();
			put(m, new Dependency(this.project));
			parse(reader, m, getTagName());
		}
	}

	protected class Dependency extends DefaultHandler {
		protected DefaultArtifact project;

		protected Dependency(DefaultArtifact a) {
			super("dependency");
			this.project = a;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			DefaultArtifact newone = new DefaultArtifact();
			Map<String, Handler> m = createArtifactParseHandlers(newone);
			put(m, new Type(newone));
			Scope scope = new Scope();
			put(m, scope);
			Optional optional = new Optional(newone);
			put(m, optional);
			parse(reader, m, getTagName());
			if (scope.isNotTest() && newone.isOptional() == false) {
				this.project.add(newone);
			}
		}
	}

	protected class Scope extends DefaultHandler {
		protected String scope;

		protected Scope() {
			super("scope");
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			this.scope = reader.getElementText();
		}

		protected boolean isNotTest() {
			return StringUtil.isEmpty(scope)
					|| "test".equalsIgnoreCase(scope) == false;
		}

	}

	protected class Optional extends DefaultHandler {

		protected DefaultArtifact a;

		protected Optional(DefaultArtifact a) {
			super("optional");
			this.a = a;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			String optional = reader.getElementText();
			a.setOptional(isOptional(optional));
		}

		protected boolean isOptional(String optional) {
			return StringUtil.isEmpty(optional) == false
					&& Boolean.parseBoolean(optional);
		}

	}

	protected class DependencyManagement extends DefaultHandler {
		protected Set<Artifact> managed;

		protected DependencyManagement(Set<Artifact> managed) {
			super("dependencyManagement");
			this.managed = managed;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			Map<String, Handler> m = new HashMap<String, Handler>();
			DefaultArtifact newone = new DefaultArtifact();
			put(m, new Dependencies(newone));
			parse(reader, m, getTagName());
			for (Artifact a : newone.getDependencies()) {
				if (validate(a)) {
					this.managed.add(a);
				}
			}
		}
	}
}
