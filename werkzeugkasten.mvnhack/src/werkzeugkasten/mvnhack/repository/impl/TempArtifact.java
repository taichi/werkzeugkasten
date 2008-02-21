/**
 * 
 */
package werkzeugkasten.mvnhack.repository.impl;

import java.util.Map;
import java.util.Set;

import werkzeugkasten.mvnhack.repository.Artifact;

class TempArtifact implements Artifact {
	private final String version;
	private final String groupId;
	private final String artifactId;

	TempArtifact(String version, String groupId, String artifactId) {
		this.version = version;
		this.groupId = groupId;
		this.artifactId = artifactId;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}

	@Override
	public String getArtifactId() {
		return artifactId;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public String getType() {
		return "jar";
	}

	@Override
	public Set<Artifact> getDependencies() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, String> getManagedDependencies() {
		throw new UnsupportedOperationException();
	}

}