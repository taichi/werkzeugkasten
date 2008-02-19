package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.net.URL;

import werkzeugkasten.common.util.UrlUtil;
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
	public URL getLocation(Artifact artifact) {
		return UrlUtil.toURL(toFile(artifact));
	}

	protected File toFile(Artifact artifact) {
		StringBuilder stb = new StringBuilder();
		stb.append(root.getAbsolutePath());
		stb.append(artifact.toPath());
		return new File(stb.toString());
	}

	@Override
	public void copyFrom(Context context, Repository repository,
			Artifact artifact) {
		DestinationUtil.copy(context, repository, artifact, this);
	}

	@Override
	public File toDestination(Artifact artifact) {
		return toFile(artifact);
	}

}
