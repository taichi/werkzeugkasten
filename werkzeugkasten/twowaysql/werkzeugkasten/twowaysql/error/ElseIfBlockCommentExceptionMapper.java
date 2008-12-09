package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;

public class ElseIfBlockCommentExceptionMapper extends AbstractExceptionMapper {

	public ElseIfBlockCommentExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_ELSEIFBLOCKCOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.C_ST: {
					return "/*";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new MissingTokenHandler(Messages.LABEL_ELSEIFBLOCKCOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.ELSEIF: {
					return "ELSEIF";
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
