package werkzeugkasten.twowaysql.tree;

import java.util.Deque;
import java.util.LinkedList;

import org.antlr.runtime.CommonToken;

public class TextNode implements QueryNode, TokenAppendable {

	protected Deque<CommonToken> tokens = new LinkedList<CommonToken>();

	protected QueryPosition position;

	public QueryPosition position() {
		return this.position;
	}

	public NodeType type() {
		return NodeType.TXTNODE;
	}

	public void append(CommonToken token) {
		this.tokens.add(token);
	}

	public void freeze() {
		if (0 < this.tokens.size()) {
			CommonToken start = this.tokens.getFirst();
			CommonToken end = this.tokens.getLast();
			this.position = new DefaultQueryPosition(start.getStartIndex(), end
					.getStopIndex(), start.getLine(), end.getLine());
			this.tokens.clear();
		}
	}
}
