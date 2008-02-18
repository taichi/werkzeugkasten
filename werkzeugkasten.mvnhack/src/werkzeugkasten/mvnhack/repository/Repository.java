package werkzeugkasten.mvnhack.repository;

import java.net.URL;

public interface Repository {

	URL getLocation(Artifact artifact);
}
