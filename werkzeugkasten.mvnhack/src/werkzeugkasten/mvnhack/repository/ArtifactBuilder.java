package werkzeugkasten.mvnhack.repository;

import java.io.InputStream;

public interface ArtifactBuilder {
	/**
	 * @notnull context
	 * @notnull pom
	 * @return maybe null
	 */
	Artifact build(Context context, InputStream pom);
}
