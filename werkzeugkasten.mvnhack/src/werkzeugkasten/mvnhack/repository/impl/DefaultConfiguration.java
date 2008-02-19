package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Configuration;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class DefaultConfiguration implements Configuration {

	protected Set<Repository> repositories = new LinkedHashSet<Repository>();

	protected Set<Destination> destinations = new LinkedHashSet<Destination>();

	protected Proxy proxy = Proxy.NO_PROXY;

	protected static final Pattern isRemote = Pattern.compile("http://.*",
			Pattern.CASE_INSENSITIVE);

	public DefaultConfiguration() {
	}

	public DefaultConfiguration(Properties properties) {
		load();
		load(properties);
	}

	protected void load() {
		File cur = new File(".", Constants.DIR_REPOSITORY);
		if (cur.exists()) {
			addLocalRepository(cur);
		}
		StringBuilder stb = new StringBuilder();
		stb.append(".m2");
		stb.append(File.pathSeparatorChar);
		stb.append(Constants.DIR_REPOSITORY);
		File usr = new File(System.getProperty("user.home"), stb.toString());
		if (usr.exists()) {
			addLocalRepository(usr);
		}
	}

	protected void addLocalRepository(File rep) {
		LocalRepository lr = new LocalRepository(rep);
		addRepository(lr);
		addDestination(lr);
	}

	protected void load(Properties properties) {
		String rs = properties.getProperty(Constants.PROP_REPOSITORY);
		if (StringUtil.isEmpty(rs) == false) {
			String[] ary = rs.split(",");
			for (String s : ary) {
				if (isRemote.matcher(s).matches() && validateURL(s)) {
					addRepository(new RemoteRepository(s));
				}
			}
		}
		UrlUtil.setUpProxy(properties.getProperty(Constants.PROP_PROXY));
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
}
