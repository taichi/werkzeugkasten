package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.nls.Messages;

public class BindingNameExceptionMapper extends AbstractExceptionMapper {

	public BindingNameExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_BINDINGNAME) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.IDENT: {
					return Messages.LABEL_IDENT;
				}
				case TwoWaySqlParser.SYM_BIND: {
					return "?";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new MissingTokenHandler(Messages.LABEL_BINDINGNAME) {
			protected String selectExpected(int expecting) {
				switch (expecting) {
				case TwoWaySqlParser.IDENT: {
					return Messages.LABEL_IDENT;
				}
				case TwoWaySqlParser.SYM_BIND: {
					return "?";
				}
				default: {
					throw new IllegalStateException();
				}
				}
			}
		});
		add(new EarlyExitHandler(Messages.LABEL_BINDINGNAME,
				Messages.REQUIRED_TXT));
	}
}
