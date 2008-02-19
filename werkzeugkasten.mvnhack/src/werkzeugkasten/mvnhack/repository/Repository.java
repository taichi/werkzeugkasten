package werkzeugkasten.mvnhack.repository;

import java.net.URL;
import java.util.Set;

public interface Repository {

	Set<URL> getLocation(Artifact artifact);
}
