package werkzeugkasten.mvnhack.repository;

import java.util.Map;
import java.util.Set;

public interface Artifact extends Comparable<Artifact> {

	String getGroupId();

	String getArtifactId();

	String getVersion();

	String getType();

	Set<Artifact> getDependencies();

	/**
	 * @return key is [groupId/artifactId] ,value is [version]
	 */
	Map<String, String> getManagedDependencies();
}
