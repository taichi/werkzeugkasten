package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.nls.Messages;

public class InBindExceptionMapper extends AbstractExceptionMapper {

	public InBindExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_INBIND) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.IN: {
					return "IN";
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
		add(new MissingTokenHandler(Messages.LABEL_INBIND) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.SYM_BIND: {
					return "?";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
	}
}
