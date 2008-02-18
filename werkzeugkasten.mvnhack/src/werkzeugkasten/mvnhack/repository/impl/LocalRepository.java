package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.net.URL;

import werkzeugkasten.common.util.UrlUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class LocalRepository implements Repository, Destination {

	protected File root;

	public LocalRepository(File root) {
		this.root = root;
	}

	@Override
	public URL getLocation(Artifact artifact) {
		return UrlUtil.toURL(toFile(artifact));
	}

	protected File toFile(Artifact artifact) {
		String ps = File.pathSeparator;
		StringBuilder stb = new StringBuilder();
		stb.append(root.getAbsolutePath());
		stb.append(ps);
		stb.append(artifact.getGroupId());
		stb.append(ps);
		stb.append(artifact.getArtifactId());
		stb.append(ps);
		stb.append(artifact.getFileName());
		return new File(stb.toString());
	}

	@Override
	public void copyFrom(Context context, Artifact artifact) {
		DestinationUtil.copy(context, artifact, new DestinationUtil.Handler() {
			@Override
			public File toDestination(Artifact artifact) {
				return toFile(artifact);
			}
		});
	}

}
