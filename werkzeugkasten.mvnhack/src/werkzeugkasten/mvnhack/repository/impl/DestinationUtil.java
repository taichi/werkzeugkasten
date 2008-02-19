package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Repository;

public class DestinationUtil {

	public static void copy(Context context, Repository repository,
			Artifact artifact, Handler handler) {
		for (URL url : repository.getLocation(artifact)) {
			InputStream in = null;
			try {
				File dest = handler.toDestination(url);
				if (dest != null) {
					File dir = dest.getParentFile();
					if (dir.exists() == false) {
						dir.mkdirs();
					}
					if (dest.exists() == false) {
						in = context.open(url);
						StreamUtil.copy(in, dest);
					}
				}
			} catch (IllegalStateException e) {
				Throwable t = e.getCause();
				if (t == null) {
					t = e;
				}
				Logger.getLogger(DestinationUtil.class.getName()).log(
						Level.WARNING, t.getMessage(), t);
			} finally {
				context.close(in);
			}
		}
	}

	interface Handler {
		File toDestination(URL url);
	}
}
