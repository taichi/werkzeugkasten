package werkzeugkasten.mvnhack.repository;

public interface Dependency extends Artifact {

	/**
	 * @notnull context
	 */
	void resolve(Context context);
}
