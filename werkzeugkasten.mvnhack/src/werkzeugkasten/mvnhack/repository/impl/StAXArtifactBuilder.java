package werkzeugkasten.mvnhack.repository.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.XMLEventParser;
import werkzeugkasten.common.util.XMLEventParser.DefaultHandler;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.ArtifactBuilder;
import werkzeugkasten.mvnhack.repository.Context;

public class StAXArtifactBuilder implements ArtifactBuilder {

	@Override
	public Artifact build(Context context, InputStream pom) {
		DefaultArtifact result = new DefaultArtifact();
		try {
			XMLEventParser parser = new XMLEventParser(pom);
			addArtifactParseHandlers(parser, result);
			parser.add(new DefaultHandler("project"));
			parser.add(new Type(result, "packaging"));
			Parent parent = new Parent();
			parser.add(parent);
			parser.add(new Dependencies(result));
			Set<Artifact> managed = new HashSet<Artifact>();
			parser.add(new DependencyManagement(managed));
			try {
				parser.parse();
			} finally {
				StreamUtil.close(pom);
			}
			resolveParent(context, parent.getArtifact());
			reconcile(context, result, parent, managed);
			if (validate(result)) {
				return result;
			}
		} catch (Exception e) {
			Constants.LOG.log(Level.WARNING, e.getMessage(), e);
		}
		return null;
	}

	protected void resolveParent(Context context, Artifact parent) {
		if (validate(parent)) {
			context.resolve(parent.getGroupId(), parent.getArtifactId(), parent
					.getVersion());
		}
	}

	protected void reconcile(Context context, DefaultArtifact result,
			Parent parent, Set<Artifact> managed) {
		Map<String, String> m = new HashMap<String, String>();
		putContextValues(m, parent.getArtifact(), "parent");

		reconcileProject(parent.getArtifact(), result, m);
		reconcileDependencies(context, result, managed, m);
	}

	protected void putContextValues(Map<String, String> m, Artifact a,
			String prefix) {
		m.put(prefix + ".groupId", a.getGroupId());
		m.put(prefix + ".artifactId", a.getArtifactId());
		m.put(prefix + ".version", a.getVersion());
	}

	protected void reconcileProject(Artifact parent, DefaultArtifact project,
			Map<String, String> m) {
		reconcile(project, project, m);
		if (StringUtil.isEmpty(project.getGroupId())) {
			project.setGroupId(parent.getGroupId());
		}
		if (StringUtil.isEmpty(project.getArtifactId())) {
			project.setArtifactId(parent.getArtifactId());
		}
		if (StringUtil.isEmpty(project.getVersion())) {
			project.setVersion(parent.getVersion());
		}
		putContextValues(m, project, "project");
	}

	protected void reconcile(Artifact src, DefaultArtifact dest,
			Map<String, String> m) {
		dest.setGroupId(StringUtil.replace(src.getGroupId(), m));
		dest.setArtifactId(StringUtil.replace(src.getArtifactId(), m));
		dest.setVersion(StringUtil.replace(src.getVersion(), m));
	}

	protected void reconcileDependencies(Context context,
			DefaultArtifact project, Set<Artifact> managed,
			Map<String, String> m) {
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

	protected void addArtifactParseHandlers(XMLEventParser parser,
			DefaultArtifact a) {
		parser.add(new GroupId(a));
		parser.add(new ArtifactId(a));
		parser.add(new Version(a));
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
			XMLEventParser parser = new XMLEventParser(reader);
			addArtifactParseHandlers(parser, a);
			parser.parse(getTagName());
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
			XMLEventParser parser = new XMLEventParser(reader);
			parser.add(new Dependency(this.project));
			parser.parse(getTagName());
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
			XMLEventParser parser = new XMLEventParser(reader);
			addArtifactParseHandlers(parser, newone);
			parser.add(new Type(newone));
			Scope scope = new Scope();
			parser.add(scope);
			Optional optional = new Optional(newone);
			parser.add(optional);
			parser.parse(getTagName());
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
			XMLEventParser parser = new XMLEventParser(reader);
			DefaultArtifact newone = new DefaultArtifact();
			parser.add(new Dependencies(newone));
			parser.parse(getTagName());
			for (Artifact a : newone.getDependencies()) {
				if (validate(a)) {
					this.managed.add(a);
				}
			}
		}
	}
}
