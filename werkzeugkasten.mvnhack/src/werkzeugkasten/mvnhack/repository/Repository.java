package werkzeugkasten.mvnhack.repository;

import java.net.URL;
import java.util.Set;

public interface Repository {

	/**
	 * @notnull groupId
	 * @notnull artifactId
	 * @notnull version
	 */
	Artifact load(String groupId, String artifactId, String version);

	/**
	 * 
	 * @notnull artifact
	 * @return set of readable resources URL candidate.
	 */
	Set<URL> getLocation(Artifact artifact);
}
