package werkzeugkasten.mvnhack.repository;

import java.util.Set;

public interface Artifact {

	String getGroupId();

	String getArtifactId();

	String getVersion();

	String getType();

	boolean isOptional();

	Set<Artifact> getDependencies();

}
