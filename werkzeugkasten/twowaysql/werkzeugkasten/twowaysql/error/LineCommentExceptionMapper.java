package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;

public class LineCommentExceptionMapper extends AbstractExceptionMapper {

	public LineCommentExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_LINECOMMENT,
				Messages.REQUIRED_LINECOMMENT));
		add(new MissingTokenHandler() {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.C_LN_ST: {
					return "--";
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
