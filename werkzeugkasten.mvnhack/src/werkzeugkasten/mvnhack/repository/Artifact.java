package werkzeugkasten.mvnhack.repository;

import java.util.Set;

public interface Artifact extends Comparable<Artifact> {

	String getGroupId();

	String getArtifactId();

	String getVersion();

	String getFileName();

	Set<Dependency> getDependencies();

}
