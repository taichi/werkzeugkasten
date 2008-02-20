package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class LocalRepository implements Repository, Destination {

	protected File root;

	protected ArtifactBuilder builder;

	public LocalRepository(File root, ArtifactBuilder builder) {
		this.root = root;
		this.builder = builder;
	}

	@Override
	public Artifact load(String groupId, String artifactId, String version) {
		try {
			File pom = new File(root, ArtifactUtil.toPom(groupId, artifactId,
					version));
			if (pom.exists()) {
				return builder.build(new FileInputStream(pom));
			}
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	@Override
	public Set<URL> getLocation(Artifact artifact) {
		Set<URL> urls = new HashSet<URL>();
		File dir = new File(root.getAbsolutePath(), ArtifactUtil
				.toPath(artifact)).getParentFile();
		if (dir.exists()) {
			for (File f : dir.listFiles()) {
				if (f.getName().startsWith(".") == false) {
					urls.add(UrlUtil.toURL(f));
				}
			}
		}
		return urls;
	}

	@Override
	public void copyFrom(Context context, Repository repository,
			Artifact artifact) {
		DestinationUtil.copy(context, repository, artifact, this);
	}

	@Override
	public File toDestination(Artifact artifact, URL from) {
		String a = ArtifactUtil.toPath(artifact);
		String f = from.getPath();
		String path = a.substring(0, a.lastIndexOf('/'));
		path += f.substring(f.lastIndexOf('/'));
		return new File(root, path);
	}

}
