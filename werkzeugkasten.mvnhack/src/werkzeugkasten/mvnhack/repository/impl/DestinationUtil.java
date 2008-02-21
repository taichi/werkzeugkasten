package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;

import werkzeugkasten.common.util.StreamUtil;
import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.FileNotFoundRuntimeException;
import werkzeugkasten.mvnhack.repository.Repository;

public class DestinationUtil {

	public static void copy(Context context, Repository repository,
			Artifact artifact, Destination destination) {
		for (URL url : repository.getLocation(artifact)) {
			InputStream in = null;
			try {
				File dest = destination.toDestination(artifact, url);
				if (dest != null && dest.exists() == false) {
					File dir = dest.getParentFile();
					if (dir.exists() == false) {
						dir.mkdirs();
					}
					in = context.open(artifact, url);
					Constants.LOG.log(Level.INFO, "copy to {0}", dest);
					StreamUtil.copy(in, dest);
				}
			} catch (FileNotFoundRuntimeException e) {
			} catch (RuntimeException e) {
				Throwable t = e.getCause();
				if (t == null) {
					t = e;
				}
				Constants.LOG.log(Level.WARNING, t.getMessage(), t);
			} finally {
				context.close(in);
			}
		}
	}
}
