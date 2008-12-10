package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.error.handler.NoViableAltHandler;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.nls.Messages;

public class EndCommentExceptionMapper extends AbstractExceptionMapper {

	public EndCommentExceptionMapper() {
		add(new NoViableAltHandler(Messages.LABEL_ENDCOMMENT,
				Messages.VIABLE_ENDCOMMENT));
		add(new MismatchedTokenHandler(Messages.LABEL_ENDCOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.END: {
					return "END";
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
		add(new MissingTokenHandler(Messages.LABEL_ENDCOMMENT) {
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
