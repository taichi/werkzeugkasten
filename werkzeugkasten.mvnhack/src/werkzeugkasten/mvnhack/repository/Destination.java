package werkzeugkasten.mvnhack.repository;

import java.io.File;
import java.net.URL;

public interface Destination {

	/**
	 * @notnull context
	 * @notnull from
	 * @notnull artifact
	 */
	void copyFrom(Context context, Repository from, Artifact artifact);

	/**
	 * @param artifact
	 * @param from
	 * @return
	 */
	File toDestination(Artifact artifact, URL from);

}
