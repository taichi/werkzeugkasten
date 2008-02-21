package werkzeugkasten.mvnhack.repository.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import werkzeugkasten.mvnhack.repository.Artifact;
import werkzeugkasten.mvnhack.repository.Context;
import werkzeugkasten.mvnhack.repository.ParentArtifact;

public class DefaultParentArtifact extends DefaultArtifact implements
		ParentArtifact {

	// ???
	protected Set<Artifact> managedDependency = new LinkedHashSet<Artifact>();

	@Override
	public void reconcile(Context context, Artifact kid) {
		// TODO Auto-generated method stub

	}

}
