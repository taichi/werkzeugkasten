package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class BindCommentExceptionMapper extends AbstractExceptionMapper {

	public BindCommentExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_BINDCOMMENT));
		add(new EarlyExitHandler(Messages.LABEL_BINDCOMMENT,
				Messages.REQUIRED_TXT));
	}
}
