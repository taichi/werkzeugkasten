package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;

public class EndCommentExceptionMapper extends AbstractExceptionMapper {

	public EndCommentExceptionMapper() {
		add(new NoViableAltHandler(Messages.LABEL_ENDCOMMENT,
				Messages.VIABLE_ENDCOMMENT));
		add(new MismatchedTokenHandler(Messages.LABEL_ENDCOMMENT) {
			protected String selectExpected(int expecting) {
				return "END";
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
