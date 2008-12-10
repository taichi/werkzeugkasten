package werkzeugkasten.twowaysql.tree.loc;

import java.util.ArrayDeque;
import java.util.Deque;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.Token;

public class LocationCalculator {
	protected State state = State.BEGIN;
	protected Deque<Token> tokens = new ArrayDeque<Token>();
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
		if (token != null) {
			this.tokens.addFirst(token);
		}
	}

	public void update(ParserRuleReturnScope scope) {
		if (scope == null) {
			return;
		}
		update(scope.start);
		if (scope.stop != null) {
			this.tokens.addLast(scope.stop);
		}
	}

	public TextLocation freeze() {
		return this.state.freeze(this);
	}

}
