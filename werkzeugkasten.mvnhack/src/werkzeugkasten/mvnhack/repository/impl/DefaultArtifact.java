package werkzeugkasten.mvnhack.repository.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Dependency;

public class DefaultArtifact implements Artifact {

	protected String groupId;

	protected String artifactId;

	protected String version;

	protected String type = "jar";

	protected Set<Dependency> dependencies = new LinkedHashSet<Dependency>();

	public DefaultArtifact() {
	}

	@Override
	public String getGroupId() {
		return this.groupId;
	}

	protected void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public String getArtifactId() {
		return this.artifactId;
	}

	protected void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

	protected void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getType() {
		return this.type;
	}

	protected void setType(String type) {
		if (StringUtil.isEmpty(type) == false) {
			this.type = type;
		}
	}

	@Override
	public String toPath() {
		char ps = '/';
		StringBuilder stb = new StringBuilder();
		stb.append(getGroupId().replace('.', '/'));
		stb.append(ps);
		stb.append(getArtifactId());
		stb.append(ps);
		stb.append(getVersion());
		stb.append(ps);
		stb.append(getArtifactId());
		stb.append('-');
		stb.append(getVersion());
		stb.append('.');
		stb.append(getType());
		return stb.toString();
	}

	@Override
	public Set<Dependency> getDependencies() {
		return this.dependencies;
	}

	protected void add(Dependency dependency) {
		this.dependencies.add(dependency);
	}

	@Override
	public int compareTo(Artifact other) {
		String[] me = { getGroupId(), getArtifactId(), getVersion() };
		String[] you = { other.getGroupId(), other.getArtifactId(),
				other.getVersion() };
		for (int i = 0; i < me.length; i++) {
			int n = me[i].compareTo(you[i]);
			if (n != 0) {
				return n;
			}
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Artifact) {
			Artifact a = (Artifact) obj;
			return equals(a);
		}
		return super.equals(obj);
	}

	public boolean equals(Artifact a) {
		return getGroupId().equals(a.getGroupId())
				&& getArtifactId().equals(a.getArtifactId())
				&& getVersion().equals(a.getVersion());
	}

	@Override
	public int hashCode() {
		StringBuilder stb = new StringBuilder();
		stb.append(getGroupId());
		stb.append(getArtifactId());
		stb.append(getVersion());
		return stb.toString().hashCode();
	}

}
