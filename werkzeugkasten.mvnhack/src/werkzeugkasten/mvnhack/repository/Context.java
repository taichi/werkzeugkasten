package werkzeugkasten.mvnhack.repository;

import java.io.InputStream;
import java.net.URL;
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

	InputStream open(URL url);

	void close(InputStream stream);
}
