package werkzeugkasten.twowaysql.tree.loc;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class LocationCalculator {
	protected State state = State.BEGIN;
	protected Deque<Token> tokens = new LinkedList<Token>();
	protected TextLocation location;

	protected enum State {
		BEGIN {
			public TextLocation freeze(LocationCalculator calc) {
				TextLocation result = TextLocation.UNKNOWN_LOCATION;
				if (0 < calc.tokens.size()) {
					CommonToken start = (CommonToken) calc.tokens.getFirst();
					CommonToken end = (CommonToken) calc.tokens.getLast();
					result = new DefaultTextLocation(start.getStartIndex(), end
							.getStopIndex(), start.getLine(), end.getLine());
					calc.tokens.clear();
				}
				calc.state = State.FREEZED;
				calc.location = result;
				return result;
			}
		},
		FREEZED {
			public TextLocation freeze(LocationCalculator calc) {
				return calc.location;
			}
		};
		public abstract TextLocation freeze(LocationCalculator calc);
	}

	public void update(Token token) {
		this.tokens.add(token);
	}

	public void update(CommonTree charactors) {
		if (charactors == null) {
			return;
		}
		if (charactors.isNil()) {
			List<?> kids = charactors.getChildren();
			CommonTree st = (CommonTree) kids.get(0);
			CommonTree ed = (CommonTree) kids.get(kids.size() - 1);
			update(st.getToken());
			update(ed.getToken());
		} else {
			update(charactors.getToken());
		}
	}

	public TextLocation freeze() {
		return this.state.freeze(this);
	}

}
