package werkzeugkasten.mvnhack.repository.impl;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.ArtifactBuilder;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.FileNotFoundRuntimeException;
import werkzeugkasten.mvnhack.repository.Repository;

public class RemoteRepository implements Repository {

	protected String baseUrl;

	protected ArtifactBuilder builder;

	public RemoteRepository(String url, ArtifactBuilder builder) {
		if (url.endsWith("/") == false) {
			url += "/";
		}
		this.baseUrl = url;
		this.builder = builder;
	}

	@Override
	public Artifact load(Context context, String groupId, String artifactId,
			String version) {
		StringBuilder stb = new StringBuilder();
		stb.append(baseUrl);
		stb.append(ArtifactUtil.toPom(groupId, artifactId, version));
		URL url = UrlUtil.toURL(stb.toString());
		try {
			return builder.build(context, context.open(ArtifactUtil.create(
					groupId, artifactId, version), url));
		} catch (FileNotFoundRuntimeException e) {
		}
		return null;
	}

	@Override
	public Set<URL> getLocation(Artifact artifact) {
		Set<URL> urls = new HashSet<URL>();
		urls.add(toURL(ArtifactUtil.toPath(artifact)));
		urls.add(toURL(ArtifactUtil.toPath(artifact, Constants.POM)));
		urls.add(toURL(ArtifactUtil.toPath(artifact, "-sources."
				+ artifact.getType())));
		return urls;
	}

	protected URL toURL(String suffix) {
		StringBuilder stb = new StringBuilder();
		stb.append(this.baseUrl);
		stb.append(suffix);
		return UrlUtil.toURL(stb.toString());
	}
}
