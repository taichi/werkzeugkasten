package werkzeugkasten.twowaysql.tree;

public interface ContainSkippable {

	void setMaybeSkip(TxtNode skip);

	TxtNode getMaybeSkip();
}
