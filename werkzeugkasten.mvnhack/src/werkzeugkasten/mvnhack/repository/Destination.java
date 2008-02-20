package werkzeugkasten.mvnhack.repository;

import java.io.File;
import java.net.URL;

public interface Destination {

	void copyFrom(Context context, Repository repository, Artifact artifact);

	File toDestination(Artifact artifact, URL from);

}
