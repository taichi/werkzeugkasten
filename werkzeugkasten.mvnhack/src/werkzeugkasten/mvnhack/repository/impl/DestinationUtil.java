package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.FileNotFoundRuntimeException;
import werkzeugkasten.mvnhack.repository.Repository;

public class DestinationUtil {

	protected static Logger logger = Logger.getLogger(Constants.NAME_LOGGER);

	public static void copy(Context context, Repository repository,
			Artifact artifact, Handler handler) {
		for (URL url : repository.getLocation(artifact)) {
			InputStream in = null;
			try {
				File dest = handler.toDestination(artifact, url);
				if (dest != null && dest.exists() == false) {
					File dir = dest.getParentFile();
					if (dir.exists() == false) {
						dir.mkdirs();
					}
					in = context.open(url);
					logger.log(Level.INFO, "copy to {0}", dest);
					StreamUtil.copy(in, dest);
				}
			} catch (FileNotFoundRuntimeException e) {
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
		File toDestination(Artifact artifact, URL from);
	}
}
