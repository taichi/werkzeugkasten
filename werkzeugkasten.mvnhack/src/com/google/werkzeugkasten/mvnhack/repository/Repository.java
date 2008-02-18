package com.google.werkzeugkasten.mvnhack.repository;

public interface Repository {

	Artifact get(Context context, String groupId, String artifactId,
			String version);
}
