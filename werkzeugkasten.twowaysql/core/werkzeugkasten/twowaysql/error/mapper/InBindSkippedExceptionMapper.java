package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class InBindSkippedExceptionMapper extends AbstractExceptionMapper {

	public InBindSkippedExceptionMapper() {
		add(new MissingTokenHandler(Messages.LABEL_INBINDSKIPPED));
		add(new MismatchedTokenHandler(Messages.LABEL_INBINDSKIPPED));
		add(new EarlyExitHandler(Messages.LABEL_INBINDSKIPPED,
				Messages.REQUIRED_INBINDSKIPPED));
	}
}
