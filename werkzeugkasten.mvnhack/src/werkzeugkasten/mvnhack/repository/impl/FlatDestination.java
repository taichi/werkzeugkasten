package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;

import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class FlatDestination implements Destination, DestinationUtil.Handler {

	protected File dest;

	public FlatDestination(File dir) {
		this.dest = dir;
	}

	@Override
	public void copyFrom(Context context, Repository repository,
			Artifact artifact) {
		DestinationUtil.copy(context, repository, artifact, this);
	}

	@Override
	public File toDestination(Artifact artifact) {
		return new File(dest, artifact.getFileName());
	}
}
