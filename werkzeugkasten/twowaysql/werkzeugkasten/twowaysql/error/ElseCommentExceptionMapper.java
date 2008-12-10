package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.error.handler.NoViableAltHandler;
import werkzeugkasten.twowaysql.error.handler.UnwantedTokenHandler;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.nls.Messages;

public class ElseCommentExceptionMapper extends AbstractExceptionMapper {

	public ElseCommentExceptionMapper() {
		add(new NoViableAltHandler(Messages.LABEL_ELSECOMMENT,
				Messages.VIABLE_ELSECOMMENT));
		add(new MismatchedTokenHandler(Messages.LABEL_ELSECOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.ELSE: {
					return "ELSE";
				}
				case TwoWaySqlParser.C_ED: {
					return "*/";
				}
				case TwoWaySqlParser.C_LN_ED: {
					return "\\n";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new MissingTokenHandler(Messages.LABEL_ELSECOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.C_ED: {
					return "*/";
				}
				case TwoWaySqlParser.C_LN_ED: {
					return "\\n";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new UnwantedTokenHandler(Messages.LABEL_ELSECOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.C_ED: {
					return "*/";
				}
				case TwoWaySqlParser.C_LN_ED: {
					return "\\n";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
	}
}
