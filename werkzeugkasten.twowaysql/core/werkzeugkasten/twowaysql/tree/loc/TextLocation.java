package werkzeugkasten.twowaysql.tree.loc;

public interface TextLocation {

	TextLocation UNKNOWN_LOCATION = new DefaultTextLocation(-1, -1, -1, -1);

	int startIndex();

	int endIndex();

	int startLine();

	int endLine();
}
