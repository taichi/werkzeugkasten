package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class IfCommentExceptionMapper extends AbstractExceptionMapper {

	public IfCommentExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_IFCOMMENT));
		add(new MissingTokenHandler(Messages.LABEL_IFCOMMENT));
		add(new EarlyExitHandler(Messages.LABEL_IFCOMMENT,
				Messages.REQUIRED_IFCOMMENT));
	}
}
