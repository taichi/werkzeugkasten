package werkzeugkasten.mvnhack.repository.impl;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Repository;

public class RemoteRepository implements Repository {

	protected String baseUrl;

	public RemoteRepository(String url) {
		if (url.endsWith("/")) {
			url = url.substring(0, url.length() - 1);
		}
		this.baseUrl = url;
	}

	@Override
	public Set<URL> getLocation(Artifact artifact) {
		Set<URL> urls = new HashSet<URL>();
		String path = artifact.toPath();
		urls.add(toURL(path));
		path = path.substring(0, path.lastIndexOf('.') - 1);
		urls.add(toURL(path + ".pom"));
		urls.add(toURL(path + "-sources." + artifact.getType()));
		return urls;
	}

	protected URL toURL(String suffix) {
		StringBuilder stb = new StringBuilder();
		stb.append(this.baseUrl);
		stb.append('/');
		stb.append(suffix);
		return UrlUtil.toURL(stb.toString());
	}
}
