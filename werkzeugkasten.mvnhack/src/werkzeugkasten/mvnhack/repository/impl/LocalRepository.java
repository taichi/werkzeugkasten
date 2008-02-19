package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class LocalRepository implements Repository, Destination,
		DestinationUtil.Handler {

	protected File root;

	public LocalRepository(File root) {
		this.root = root;
	}

	@Override
	public Set<URL> getLocation(Artifact artifact) {
		Set<URL> urls = new HashSet<URL>();
		File dir = new File(root.getAbsolutePath(), artifact.toPath())
				.getParentFile();
		for (File f : dir.listFiles()) {
			urls.add(UrlUtil.toURL(f));
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
