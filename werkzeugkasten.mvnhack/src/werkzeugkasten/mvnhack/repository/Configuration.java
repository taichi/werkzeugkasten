package werkzeugkasten.mvnhack.repository;

import java.util.List;

public interface Configuration {

	List<Repository> getRepositories();

	void addRepository(Repository repository);

	List<Destination> getDestinations();

	void addDestination(Destination destination);

}
