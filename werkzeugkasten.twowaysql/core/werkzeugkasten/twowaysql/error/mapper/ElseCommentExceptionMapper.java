package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.error.handler.NoViableAltHandler;
import werkzeugkasten.twowaysql.error.handler.UnwantedTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class ElseCommentExceptionMapper extends AbstractExceptionMapper {

	public ElseCommentExceptionMapper() {
		add(new NoViableAltHandler(Messages.LABEL_ELSECOMMENT,
				Messages.VIABLE_ELSECOMMENT));
		add(new MismatchedTokenHandler(Messages.LABEL_ELSECOMMENT));
		add(new MissingTokenHandler(Messages.LABEL_ELSECOMMENT));
		add(new UnwantedTokenHandler(Messages.LABEL_ELSECOMMENT));
	}
}
