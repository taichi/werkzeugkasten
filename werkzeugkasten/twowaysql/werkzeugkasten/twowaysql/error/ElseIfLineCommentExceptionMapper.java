package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.nls.Messages;

public class ElseIfLineCommentExceptionMapper extends AbstractExceptionMapper {

	public ElseIfLineCommentExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_ELSEIFLINECOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.C_LN_ST: {
					return "--";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new MissingTokenHandler(Messages.LABEL_ELSEIFLINECOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				// case TwoWaySqlParser.C_LN_ST: {
				// return "--";
				// }
				case TwoWaySqlParser.ELSEIF: {
					return "ELSEIF";
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
