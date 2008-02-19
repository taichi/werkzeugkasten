package werkzeugkasten.mvnhack.repository.impl;

import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.Dependency;

public class DefaultDependency extends DefaultArtifact implements Dependency {

	public DefaultDependency() {
	}

	@Override
	public void resolve(Context context) {
		context.resolve(getGroupId(), getArtifactId(), getVersion());
	}

}
