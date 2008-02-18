package werkzeugkasten.mvnhack.repository.impl;

import java.io.File;

import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Destination;
import werkzeugkasten.mvnhack.repository.Repository;

public class LocalRepository implements Repository, Destination {

	protected File root;

	public LocalRepository(File root) {
		this.root = root;
	}

	@Override
	public Artifact get(Context context, String groupId, String artifactId,
			String version) {
		if (context.isResolvedArtifact(groupId, artifactId, version)) {
			return context.getResolvedArtifact(groupId, artifactId, version);
		}
		return null;
	}

	@Override
	public void copyFrom(Context context, Artifact artifact) {
		DestinationUtil.copy(context, artifact, new DestinationUtil.Handler() {
			@Override
			public File toDestination(Artifact artifact) {
				String ps = File.pathSeparator;
				StringBuilder stb = new StringBuilder();
				stb.append(root.getAbsolutePath());
				stb.append(ps);
				stb.append(artifact.getGroupId());
				stb.append(ps);
				stb.append(artifact.getArtifactId());
				stb.append(ps);
				stb.append(artifact.getFileName());
				return new File(stb.toString());
			}
		});
	}

}
