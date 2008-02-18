package werkzeugkasten.mvnhack.repository.impl;

import java.util.Set;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Dependency;

public class DefaultArtifact implements Artifact {

	protected String groupId;

	protected String artifactId;

	protected String version;

	protected String type = "jar";

	protected Set<Dependency> dependencies;

	public DefaultArtifact(String groupId, String artivactId, String version,
			String type) {
		this.groupId = groupId;
		this.artifactId = artivactId;
		this.version = version;
		if (StringUtil.isEmpty(type) == false) {
			this.type = type;
		}
	}

	@Override
	public String getGroupId() {
		return this.groupId;
	}

	@Override
	public String getArtifactId() {
		return this.artifactId;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

	protected String getType() {
		return this.type;
	}

	@Override
	public String getFileName() {
		StringBuilder stb = new StringBuilder();
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

}
