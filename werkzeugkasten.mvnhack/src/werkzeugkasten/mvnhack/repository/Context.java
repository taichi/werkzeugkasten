package werkzeugkasten.mvnhack.repository;

import java.io.InputStream;
import java.net.URL;

public interface Context {

	void addResolvedArtifact(Artifact artifact);

	boolean isResolvedArtifact(String groupId, String artifactId, String version);

	InputStream open(URL url);

	void close(InputStream stream);
}
