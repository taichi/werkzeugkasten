package werkzeugkasten.mvnhack.repository.impl;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Configuration;
import werkzeugkasten.mvnhack.repository.Context;

public class DefaultContext implements Context {

	protected Map<String, Artifact> resolved = new HashMap<String, Artifact>();

	protected Configuration configuration;

	public DefaultContext(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public boolean isResolvedArtifact(String groupId, String artifactId,
			String version) {
		char ps = '/';
		StringBuilder stb = new StringBuilder();
		stb.append(groupId);
		stb.append(ps);
		stb.append(artifactId);
		stb.append(ps);
		stb.append(version);
		return resolved.containsKey(stb.toString());
	}

	@Override
	public void resolve(String groupId, String artifactId, String version) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream open(URL url) {
		return UrlUtil.open(url);
	}

	@Override
	public void close(InputStream stream) {
		StreamUtil.close(stream);
	}
}
