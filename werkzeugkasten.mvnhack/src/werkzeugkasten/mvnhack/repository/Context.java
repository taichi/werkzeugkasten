package werkzeugkasten.mvnhack.repository;

import java.io.InputStream;

public interface Context {

	void addResolvedArtifact(Artifact artifact);

	Artifact getResolvedArtifact(String groupId, String artifactId,
			String version);

	boolean isResolvedArtifact(String groupId, String artifactId, String version);

	InputStream open(Artifact artifact);

	void close(InputStream stream);
}
