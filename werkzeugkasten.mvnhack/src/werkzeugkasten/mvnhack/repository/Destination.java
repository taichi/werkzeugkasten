package werkzeugkasten.mvnhack.repository;

public interface Destination {

	void copyFrom(Context context, Repository repository, Artifact artifact);
}
