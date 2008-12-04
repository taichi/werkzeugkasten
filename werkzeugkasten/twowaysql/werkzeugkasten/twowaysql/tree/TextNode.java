package werkzeugkasten.twowaysql.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class TextNode implements QueryNode {

	protected Deque<Token> tokens = new LinkedList<Token>();

	protected QueryPosition position;

	public QueryPosition position() {
		return this.position;
	}

	public NodeType type() {
		return NodeType.TXTNODE;
	}

	public void append(Token token) {
		this.tokens.add(token);
	}

	public void append(CommonTree charactors) {
		if (charactors == null) {
			return;
		}
		if (charactors.isNil()) {
			List<?> kids = charactors.getChildren();
			CommonTree st = (CommonTree) kids.get(0);
			CommonTree ed = (CommonTree) kids.get(kids.size() - 1);
			append(st.getToken());
			append(ed.getToken());
		} else {
			append(charactors.getToken());
		}
	}

	public void freeze() {
		if (0 < this.tokens.size()) {
			CommonToken start = (CommonToken) this.tokens.getFirst();
			CommonToken end = (CommonToken) this.tokens.getLast();
			this.position = new DefaultQueryPosition(start.getStartIndex(), end
					.getStopIndex(), start.getLine(), end.getLine());
			System.out.println(this.tokens);
			this.tokens.clear();
		}
	}
}
