package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class LocalRepository implements Repository, Destination,
		DestinationUtil.Handler {

	protected File root;

	protected ArtifactBuilder builder;

	public LocalRepository(File root, ArtifactBuilder builder) {
		this.root = root;
		this.builder = builder;
	}

	@Override
	public Artifact load(String groupId, String artifactId, String version) {
		if (StringUtil.isEmpty(groupId)) {
			groupId = artifactId;
		}
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
		File dir = new File(root.getAbsolutePath(), artifact.toPath())
				.getParentFile();
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
	public File toDestination(URL url) {
		String path = url.getPath();
		int index = path.indexOf(Constants.DIR_REPOSITORY);
		int length = Constants.DIR_REPOSITORY.length();
		path = path.substring(index + length, path.length());
		return new File(root, path);
	}

}
