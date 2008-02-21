package werkzeugkasten.mvnhack.repository.impl;

import werkzeugkasten.mvnhack.Constants;
import werkzeugkasten.mvnhack.repository.Artifact;

public class ArtifactUtil {

	public static final Artifact create(final String groupId,
			final String artifactId, final String version) {
		return new TempArtifact(version, groupId, artifactId);
	}

	public static final String toPom(String groupId, String artifactId,
			String version) {
		return toPath(groupId, artifactId, version, Constants.POM);
	}

	public static String toPath(String groupId, String artifactId,
			String version, String extension) {
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
		stb.append(extension);
		return stb.toString();
	}

	public static String toPath(Artifact artifact) {
		return toPath(artifact.getGroupId(), artifact.getArtifactId(), artifact
				.getVersion(), "." + artifact.getType());
	}

	public static String toPath(Artifact artifact, String extension) {
		return toPath(artifact.getGroupId(), artifact.getArtifactId(), artifact
				.getVersion(), extension);
	}

}
