package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.error.handler.UnwantedTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class TwoWaySqlExceptionMapper extends AbstractExceptionMapper {

	public TwoWaySqlExceptionMapper() {
		add(new UnwantedTokenHandler(Messages.LABEL_TWOWAYSQL));
		add(new MissingTokenHandler(Messages.LABEL_TWOWAYSQL));
		add(new MismatchedTokenHandler(Messages.LABEL_TWOWAYSQL));
	}
}
