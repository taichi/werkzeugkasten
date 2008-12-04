package werkzeugkasten.twowaysql.tree;

public interface QueryPosition {

	int startIndex();

	int endIndex();

	int startLine();

	int endLine();
}
