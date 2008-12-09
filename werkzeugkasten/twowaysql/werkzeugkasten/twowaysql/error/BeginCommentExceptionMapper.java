package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;

public class BeginCommentExceptionMapper extends AbstractExceptionMapper {

	public BeginCommentExceptionMapper() {
		add(new NoViableAltHandler(Messages.LABEL_BEGINCOMMENT,
				Messages.VIABLE_BEGINCOMMENT));
		add(new MismatchedTokenHandler(Messages.LABEL_BEGINCOMMENT) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.BEGIN: {
					return "BEGIN";
				}
				case TwoWaySqlParser.C_LN_ED: {
					return "\\n";
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
		add(new MissingTokenHandler(Messages.LABEL_BEGINCOMMENT) {
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
		add(new EarlyExitHandler(Messages.LABEL_BEGINCOMMENT,
				Messages.REQUIRED_BEGINCOMMENT));
	}
}
