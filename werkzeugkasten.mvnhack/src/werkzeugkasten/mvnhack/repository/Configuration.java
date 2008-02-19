package werkzeugkasten.mvnhack.repository;

import java.util.Set;

public interface Configuration {

	Set<Repository> getRepositories();

	void addRepository(Repository repository);

	Set<Destination> getDestinations();

	void addDestination(Destination destination);

}
