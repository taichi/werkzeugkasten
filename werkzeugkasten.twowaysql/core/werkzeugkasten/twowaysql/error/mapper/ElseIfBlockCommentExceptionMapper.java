package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class ElseIfBlockCommentExceptionMapper extends AbstractExceptionMapper {

	public ElseIfBlockCommentExceptionMapper() {
		add(new MismatchedTokenHandler(Messages.LABEL_ELSEIFBLOCKCOMMENT));
		add(new MissingTokenHandler(Messages.LABEL_ELSEIFBLOCKCOMMENT));
	}
}
