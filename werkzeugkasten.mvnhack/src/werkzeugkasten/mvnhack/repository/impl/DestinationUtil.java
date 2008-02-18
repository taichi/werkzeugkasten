package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.InputStream;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;

public class DestinationUtil {

	public static void copy(Context context, Artifact artifact, Handler handler) {
		InputStream in = null;
		try {
			File lib = handler.toDestination(artifact);
			if (lib.exists() == false) {
				in = context.open(artifact);
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
