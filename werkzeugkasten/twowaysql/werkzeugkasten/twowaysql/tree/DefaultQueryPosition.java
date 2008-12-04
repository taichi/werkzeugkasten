package werkzeugkasten.twowaysql.tree;

public class DefaultQueryPosition implements QueryPosition {
	protected int startIndex;
	protected int endIndex;
	protected int startLine;
	protected int endLine;

	public DefaultQueryPosition(int startIndex, int endIndex, int startLine,
			int endLine) {
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.startLine = startLine;
		this.endLine = endLine;
	}

	public int startIndex() {
		return this.startIndex;
	}

	public int endIndex() {
		return this.endIndex;
	}

	public int startLine() {
		return this.startLine;
	}

	public int endLine() {
		return this.endLine;
	}

}
