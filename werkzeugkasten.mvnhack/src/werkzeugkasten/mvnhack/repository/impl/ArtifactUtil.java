package werkzeugkasten.mvnhack.repository.impl;

import werkzeugkasten.mvnhack.Constants;

public class ArtifactUtil {

	public static final String toPom(String groupId, String artifactId,
			String version) {
		char ps = '/';
		StringBuilder stb = new StringBuilder();
		stb.append(groupId.replace('.', '/'));
		stb.append(ps);
		stb.append(artifactId);
		stb.append(ps);
		stb.append(version);
		stb.append(ps);
		stb.append(artifactId);
		stb.append('-');
		stb.append(version);
		stb.append(Constants.POM);
		return stb.toString();
	}

}
