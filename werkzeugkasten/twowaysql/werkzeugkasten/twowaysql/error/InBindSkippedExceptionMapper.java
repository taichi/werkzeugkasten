package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;

public class InBindSkippedExceptionMapper extends AbstractExceptionMapper {

	public InBindSkippedExceptionMapper() {
		add(new MissingTokenHandler(Messages.LABEL_INBINDSKIPPED) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.SYM_LP: {
					return "(";
				}
				case TwoWaySqlParser.SYM_RP: {
					return ")";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new MismatchedTokenHandler(Messages.LABEL_INBINDSKIPPED) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.SYM_LP: {
					return "(";
				}
				case TwoWaySqlParser.SYM_RP: {
					return ")";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new EarlyExitHandler(Messages.LABEL_INBINDSKIPPED,
				Messages.REQUIRED_INBINDSKIPPED));
	}
}
