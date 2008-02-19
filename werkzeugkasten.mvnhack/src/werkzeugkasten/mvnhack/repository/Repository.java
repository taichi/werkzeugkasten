package werkzeugkasten.mvnhack.repository;

import java.net.URL;
import java.util.Set;

public interface Repository {

	Artifact load(String groupId, String artifactId, String version);

	Set<URL> getLocation(Artifact artifact);
}
