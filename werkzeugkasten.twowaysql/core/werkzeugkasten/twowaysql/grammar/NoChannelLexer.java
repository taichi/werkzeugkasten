package werkzeugkasten.twowaysql.grammar;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;

public class NoChannelLexer extends TwoWaySqlLexer {

	public NoChannelLexer() {
	}

	public NoChannelLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}

	public NoChannelLexer(CharStream input, RecognizerSharedState state) {
		super(input, state);

	}

	@Override
	public Token emit() {
		state.channel = org.antlr.runtime.Token.DEFAULT_CHANNEL;
		return super.emit();
	}
}
