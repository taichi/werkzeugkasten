package werkzeugkasten.mvnhack.repository;

import java.io.InputStream;

public interface ArtifactBuilder {
	Artifact build(Context context, InputStream pom);
}
