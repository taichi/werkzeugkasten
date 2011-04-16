package werkzeugkasten.mvnhack;

import java.util.logging.Logger;

public class Constants {

	public static final String NAME_LOGGER = "werkzeugkasten.mvnhack";

	public static final Logger LOG = Logger.getLogger(NAME_LOGGER);

	public static final String PROP_REPOSITORY = "repository";

	public static final String PROP_PROXY = "proxy";

	public static final String PROP_NONPROXY = "http.nonProxyHosts";

	public static final String DIR_REPOSITORY = "repository";

	public static final String POM = ".pom";

	public static final String CENTRAL_REPOSITORY = "http://repo1.maven.org/maven2/";

	public static final String NODE_REPOSITORIES = "repositories";
}
