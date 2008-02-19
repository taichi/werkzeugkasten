package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Repository;

public class DestinationUtil {

	public static void copy(Context context, Repository repository,
			Artifact artifact, Handler handler) {
		InputStream in = null;
		try {
			File lib = handler.toDestination(artifact);
			if (lib.exists() == false) {
				URL url = repository.getLocation(artifact);
				in = context.open(url);
				StreamUtil.copy(in, lib);
			}
		} finally {
			context.close(in);
		}

	}

	interface Handler {
		File toDestination(Artifact artifact);
	}
}
