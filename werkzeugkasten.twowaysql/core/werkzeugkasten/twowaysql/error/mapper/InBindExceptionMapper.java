package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class InBindExceptionMapper extends AbstractExceptionMapper {

	public InBindExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_INBIND));
		add(new MissingTokenHandler(Messages.LABEL_INBIND));
	}
}
