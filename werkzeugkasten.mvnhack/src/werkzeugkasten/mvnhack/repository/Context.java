package werkzeugkasten.mvnhack.repository;

import java.io.InputStream;
import java.util.List;

public interface Context {

	List<Repository> getRepositories();

	void addRepository(Repository repository);

	List<Destination> getDestinations();

	void addDestination(Destination destination);

	void addResolvedArtifact(Artifact artifact);

	Artifact getResolvedArtivact(String groupId, String artifactId,
			String version);

	boolean isResolvedArtifact(String groupId, String artifactId, String version);

	InputStream open(Artifact artifact);

	void close(InputStream stream);
}
