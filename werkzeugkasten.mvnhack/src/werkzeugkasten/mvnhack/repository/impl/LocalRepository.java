package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;

import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class LocalRepository implements Repository, Destination {

	protected File root;

	public LocalRepository(File root) {
		this.root = root;
	}

	@Override
	public Artifact get(Context context, String groupId, String artifactId,
			String version) {
		if (context.isResolvedArtifact(groupId, artifactId, version)) {
			return context.getResolvedArtivact(groupId, artifactId, version);
		}
		return null;
	}

	@Override
	public void copyFrom(Context context, Artifact artifact) {
	}

}
