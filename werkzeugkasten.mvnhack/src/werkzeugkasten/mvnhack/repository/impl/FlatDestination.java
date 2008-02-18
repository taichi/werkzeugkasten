package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;

import werkzeugkasten.mvnhack.repository.Artifact;

public class FlatDestination extends AbstractDestinationTemplate {

	protected File dest;

	public FlatDestination(File dest) {
		this.dest = dest;
	}

	@Override
	protected File toDestination(Artifact artifact) {
		return new File(dest, artifact.getFileName());
	}
}
