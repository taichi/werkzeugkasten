package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.InputStream;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;

public abstract class AbstractDestinationTemplate implements Destination {

	@Override
	public void copyFrom(Context context, Artifact artifact) {
		InputStream in = null;
		try {
			File lib = toDestination(artifact);
			in = context.open(artifact);
			StreamUtil.copy(in, lib);
		} finally {
			StreamUtil.close(in);
		}
	}

	protected abstract File toDestination(Artifact artifact);
}
