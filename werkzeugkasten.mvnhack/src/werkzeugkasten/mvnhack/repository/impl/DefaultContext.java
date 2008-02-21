package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import werkzeugkasten.common.util.FileUtil;
import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Configuration;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Dependency;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class DefaultContext implements Context {

	protected Map<String, Artifact> resolved;

	protected Configuration configuration;

	public DefaultContext(Configuration configuration) {
		this.configuration = configuration;
		this.resolved = new HashMap<String, Artifact>();
	}

	protected void addResolved(Artifact artifact) {
		resolved.put(toId(artifact.getGroupId(), artifact.getArtifactId(),
				artifact.getVersion()), artifact);
	}

	protected String toId(String groupId, String artifactId, String version) {
		return ArtifactUtil.toPath(groupId, artifactId, version, "");
	}

	@Override
	public Artifact resolve(String groupId, String artifactId, String version) {
		String id = toId(groupId, artifactId, version);
		Artifact result = resolved.get(id);
		if (result == null) {
			for (Repository r : configuration.getRepositories()) {
				Artifact a = r.load(this, groupId, artifactId, version);
				if (a != null) {
					for (Destination d : configuration.getDestinations()) {
						d.copyFrom(this, r, a);
					}
					addResolved(a);
					for (Dependency d : a.getDependencies()) {
						d.resolve(this);
					}
					return a;
				}
			}
		}
		return result;
	}

	@Override
	public InputStream open(Artifact artifact, URL url) {
		InputStream result = null;
		Object from = null;
		for (Destination d : configuration.getDestinations()) {
			File f = d.toDestination(artifact, url);
			if (f != null && f.exists()) {
				result = FileUtil.open(f);
				from = f;
			}
		}
		if (result == null) {
			result = UrlUtil.open(url);
			from = url;
		}
		Constants.LOG.log(Level.INFO, "read from {0}", from);
		return result;
	}

	@Override
	public void close(InputStream stream) {
		StreamUtil.close(stream);
	}
}
