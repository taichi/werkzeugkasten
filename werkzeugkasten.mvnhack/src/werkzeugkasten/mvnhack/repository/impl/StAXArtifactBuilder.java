package werkzeugkasten.mvnhack.repository.impl;

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
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.ArtifactBuilder;
import werkzeugkasten.mvnhack.repository.Context;

public class StAXArtifactBuilder implements ArtifactBuilder {

	@Override
	public Artifact build(Context context, InputStream pom) {
		DefaultArtifact result = new DefaultArtifact();
		Map<String, Handler> handlers = createArtifactParseHandlers(result);
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
			return result;
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
		reader = factory.createFilteredReader(reader, new StreamFilter() {
			@Override
			public boolean accept(XMLStreamReader reader) {
				return reader.isStartElement() || reader.isEndElement();
			}
		});
		return reader;
	}

	protected void parse(XMLStreamReader reader, Map<String, Handler> handlers,
			String end) throws XMLStreamException {
		for (; reader.hasNext();) {
			int event = reader.next();
			String localname = reader.getLocalName();
			if (XMLStreamConstants.START_ELEMENT == event) {
				Handler handler = handlers.get(localname);
				if (handler == null) {
					skipTo(reader, localname);
				} else {
					handler.handle(reader);
				}
			} else if (XMLStreamConstants.END_ELEMENT == event
					&& end.equals(localname)) {
				return;
			}
		}
	}

	protected void skipTo(XMLStreamReader reader, String end)
			throws XMLStreamException {
		for (; reader.hasNext();) {
			if (XMLStreamConstants.END_ELEMENT == reader.next()) {
				if (end.equals(reader.getLocalName())) {
					break;
				}
			}
		}
	}

	protected interface Handler {
		String getTagName();

		void handle(XMLStreamReader reader) throws XMLStreamException;
	}

	protected void put(Map<String, Handler> m, Handler h) {
		m.put(h.getTagName(), h);
	}

	protected Map<String, Handler> createArtifactParseHandlers(DefaultArtifact a) {
		Map<String, Handler> m = new HashMap<String, Handler>();
		put(m, new GroupId(a));
		put(m, new ArtifactId(a));
		put(m, new Version(a));
		return m;
	}

	protected class GroupId implements Handler {
		protected DefaultArtifact a;

		protected GroupId(DefaultArtifact a) {
			this.a = a;
		}

		@Override
		public String getTagName() {
			return "groupId";
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			a.setGroupId(reader.getElementText());
		}
	}

	protected class ArtifactId implements Handler {
		protected DefaultArtifact a;

		protected ArtifactId(DefaultArtifact a) {
			this.a = a;
		}

		@Override
		public String getTagName() {
			return "artifactId";
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			a.setArtifactId(reader.getElementText());
		}
	}

	protected class Version implements Handler {
		protected DefaultArtifact a;

		protected Version(DefaultArtifact a) {
			this.a = a;
		}

		@Override
		public String getTagName() {
			return "version";
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			a.setVersion(reader.getElementText());
		}
	}

	protected class Type implements Handler {
		protected DefaultArtifact a;
		protected String tag;

		protected Type(DefaultArtifact a) {
			this(a, "type");
		}

		protected Type(DefaultArtifact a, String tag) {
			this.a = a;
			this.tag = tag;
		}

		@Override
		public String getTagName() {
			return tag;
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			a.setType(reader.getElementText());
		}
	}

	protected class Parent implements Handler {
		protected DefaultArtifact a;

		protected Parent() {
			this.a = new DefaultArtifact();
		}

		public Artifact getArtifact() {
			return this.a;
		}

		@Override
		public String getTagName() {
			return "parent";
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			Map<String, Handler> m = createArtifactParseHandlers(a);
			parse(reader, m, getTagName());
		}
	}

	protected class Dependencies implements Handler {
		protected DefaultArtifact project;

		protected Dependencies(DefaultArtifact project) {
			this.project = project;
		}

		@Override
		public String getTagName() {
			return "dependencies";
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			Map<String, Handler> m = new HashMap<String, Handler>();
			put(m, new Dependency(this.project));
			parse(reader, m, getTagName());
		}
	}

	protected class Dependency implements Handler {
		protected DefaultArtifact project;

		protected Dependency(DefaultArtifact a) {
			this.project = a;
		}

		@Override
		public String getTagName() {
			return "dependency";
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			DefaultArtifact newone = new DefaultArtifact();
			Map<String, Handler> m = createArtifactParseHandlers(newone);
			put(m, new Type(newone));
			Scope scope = new Scope();
			put(m, scope);
			Optional optional = new Optional();
			put(m, optional);
			parse(reader, m, getTagName());
			if (scope.isNotTest() && optional.isNotOptional()) {
				this.project.add(newone);
			}
		}
	}

	protected class Scope implements Handler {
		protected String scope;

		@Override
		public String getTagName() {
			return "scope";
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

	protected class Optional implements Handler {

		private String optional;

		@Override
		public String getTagName() {
			return "optional";
		}

		@Override
		public void handle(XMLStreamReader reader) throws XMLStreamException {
			this.optional = reader.getElementText();
		}

		protected boolean isNotOptional() {
			return StringUtil.isEmpty(optional)
					|| Boolean.parseBoolean(optional) == false;
		}

	}

	protected class DependencyManagement implements Handler {
		protected Set<Artifact> managed;

		protected DependencyManagement(Set<Artifact> managed) {
			this.managed = managed;
		}

		@Override
		public String getTagName() {
			return "dependencyManagement";
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
