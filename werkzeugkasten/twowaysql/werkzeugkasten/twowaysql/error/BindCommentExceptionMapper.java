package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;

public class BindCommentExceptionMapper extends AbstractExceptionMapper {

	public BindCommentExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_BINDCOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.SYM_BIND: {
					return "?";
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
	}
}
