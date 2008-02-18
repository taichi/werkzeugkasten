package werkzeugkasten.mvnhack.repository;

import java.net.URL;
import java.util.List;

public interface Artifact extends Comparable<Artifact> {

	String getGroupId();

	String getArtifactId();

	String getVersion();

	String getFileName();

	List<Dependency> getDependencies();

	URL toURL(Context context);
}
