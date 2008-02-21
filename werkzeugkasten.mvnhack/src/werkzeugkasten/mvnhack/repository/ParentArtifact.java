package werkzeugkasten.mvnhack.repository;

public interface ParentArtifact extends Artifact {

	/**
	 * @notnull context
	 * @notnull kid
	 */
	void reconcile(Context context, Artifact kid);
}
