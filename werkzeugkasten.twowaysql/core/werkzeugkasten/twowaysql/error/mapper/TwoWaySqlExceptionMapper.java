package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.UnwantedTokenHandler;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.nls.Messages;

public class TwoWaySqlExceptionMapper extends AbstractExceptionMapper {

	public TwoWaySqlExceptionMapper() {
		add(new UnwantedTokenHandler(Messages.LABEL_TWOWAYSQL) {
			@Override
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.EOF: {
					return "EOF";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});

		// TODO .....
		add(new MismatchedTokenHandler(Messages.LABEL_BINDCOMMENT) {
			@Override
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
		add(new EarlyExitHandler(Messages.LABEL_BINDCOMMENT,
				Messages.REQUIRED_TXT));
	}
}
