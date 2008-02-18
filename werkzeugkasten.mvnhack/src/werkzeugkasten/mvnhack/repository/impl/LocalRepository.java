package werkzeugkasten.mvnhack.repository.impl;

import java.net.URL;

import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;


public class LocalRepository implements Repository, Destination {

	@Override
	public Artifact get(Context context, String groupId, String artifactId,
			String version) {
		if (context.isResolvedArtifact(groupId, artifactId, version)) {
			return context.getResolvedArtivact(groupId, artifactId, version);
		}
		return null;
	}

	@Override
	public void copyFrom(Context context, URL url) {
		try {

		} finally {

		}
	}

}
