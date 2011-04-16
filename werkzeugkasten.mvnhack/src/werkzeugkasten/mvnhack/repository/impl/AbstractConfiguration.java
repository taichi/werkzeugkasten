package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;

import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.ArtifactBuilder;
import werkzeugkasten.mvnhack.repository.Configuration;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public abstract class AbstractConfiguration implements Configuration {

	protected Set<Repository> repositories = new LinkedHashSet<Repository>();
	protected Set<Destination> destinations = new LinkedHashSet<Destination>();
	protected ArtifactBuilder builder = new StAXArtifactBuilder();

	protected void addLocalRepository(File rep) {
		Constants.LOG.log(Level.INFO, "LocalRepository :{0}", rep.toString());
		LocalRepository lr = new LocalRepository(rep, builder);
		addRepository(lr);
		addDestination(lr);
	}

	protected boolean validateURL(String url) {
		try {
			new URL(url);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

	@Override
	public void addDestination(Destination destination) {
		this.destinations.add(destination);
	}

	@Override
	public void addRepository(Repository repository) {
		this.repositories.add(repository);
	}

	@Override
	public Set<Destination> getDestinations() {
		return this.destinations;
	}

	@Override
	public Set<Repository> getRepositories() {
		return this.repositories;
	}

	protected void load() {
		File cur = new File(".", Constants.DIR_REPOSITORY);
		if (cur.exists()) {
			addLocalRepository(cur);
		}
		StringBuilder stb = new StringBuilder();
		stb.append(".m2");
		stb.append('/');
		stb.append(Constants.DIR_REPOSITORY);
		File usr = new File(System.getProperty("user.home"), stb.toString());
		if (usr.exists()) {
			addLocalRepository(usr);
		}
	
		addRepository(new RemoteRepository(Constants.CENTRAL_REPOSITORY,
				builder));
	}

}
