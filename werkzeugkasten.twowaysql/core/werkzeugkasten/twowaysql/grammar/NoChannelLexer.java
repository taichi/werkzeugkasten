package werkzeugkasten.twowaysql.grammar;

import org.antlr.runtime.Token;

public class NoChannelLexer extends TwoWaySqlLexer {
	@Override
	public Token emit() {
		state.channel = org.antlr.runtime.Token.DEFAULT_CHANNEL;
		return super.emit();
	}
}
