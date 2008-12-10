package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.nls.Messages;

public class IfCommentExceptionMapper extends AbstractExceptionMapper {

	public IfCommentExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_IFCOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.IF: {
					return "IF";
				}
				case TwoWaySqlParser.C_ST: {
					return "/*";
				}
				case TwoWaySqlParser.C_ED: {
					return "*/";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new MissingTokenHandler(Messages.LABEL_IFCOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.IF: {
					return "IF";
				}
				case TwoWaySqlParser.C_ST: {
					return "/*";
				}
				case TwoWaySqlParser.C_ED: {
					return "*/";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new EarlyExitHandler(Messages.LABEL_IFCOMMENT,
				Messages.REQUIRED_IFCOMMENT));
	}
}
