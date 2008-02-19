package werkzeugkasten.mvnhack.repository.impl;

import java.net.URL;

import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Repository;

public class RemoteRepository implements Repository {

	protected String baseUrl;

	public RemoteRepository(String url) {
		this.baseUrl = url;
	}

	@Override
	public URL getLocation(Artifact artifact) {
		char ps = '/';
		StringBuilder stb = new StringBuilder();
		stb.append(baseUrl);
		stb.append(ps);
		stb.append(artifact.getGroupId());
		stb.append(ps);
		stb.append(artifact.getArtifactId());
		stb.append(ps);
		stb.append(artifact.getFileName());
		return UrlUtil.toURL(stb.toString());
	}

}
