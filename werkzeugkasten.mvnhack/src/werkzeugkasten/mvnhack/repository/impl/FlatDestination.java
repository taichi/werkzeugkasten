package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;

import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;

public class FlatDestination implements Destination {

	protected File dest;

	public FlatDestination(File dir) {
		this.dest = dir;
	}

	@Override
	public void copyFrom(Context context, Artifact artifact) {
		DestinationUtil.copy(context, artifact, new DestinationUtil.Handler() {
			@Override
			public File toDestination(Artifact artifact) {
				return new File(dest, artifact.getFileName());
			}
		});
	}
}
