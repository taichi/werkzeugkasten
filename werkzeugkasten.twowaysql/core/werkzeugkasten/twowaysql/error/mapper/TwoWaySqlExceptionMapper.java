package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
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

		add(new MissingTokenHandler(Messages.LABEL_TWOWAYSQL) {
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
		add(new MismatchedTokenHandler(Messages.LABEL_TWOWAYSQL) {
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
	}
}
