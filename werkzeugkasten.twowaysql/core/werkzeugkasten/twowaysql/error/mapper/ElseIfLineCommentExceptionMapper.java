package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class ElseIfLineCommentExceptionMapper extends AbstractExceptionMapper {

	public ElseIfLineCommentExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_ELSEIFLINECOMMENT));
		add(new MissingTokenHandler(Messages.LABEL_ELSEIFLINECOMMENT));
	}
}
