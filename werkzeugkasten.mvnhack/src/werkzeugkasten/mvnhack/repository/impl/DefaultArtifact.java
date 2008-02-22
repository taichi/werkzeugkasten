package werkzeugkasten.mvnhack.repository.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.mvnhack.repository.Artifact;

public class DefaultArtifact implements Artifact {

	protected String groupId;

	protected String artifactId;

	protected String version;

	protected String type = "jar";

	protected Set<Artifact> dependencies = new LinkedHashSet<Artifact>();

	protected Map<String, String> managedDependencies;

	public DefaultArtifact() {
	}

	public DefaultArtifact(Map<String, String> managedDependencies) {
		this.managedDependencies = managedDependencies;
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
		this.type = StringUtil.toString(type, "jar");
	}

	@Override
	public Set<Artifact> getDependencies() {
		return this.dependencies;
	}

	protected void add(Artifact dependency) {
		this.dependencies.add(dependency);
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
