package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.error.handler.NoViableAltHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class EndCommentExceptionMapper extends AbstractExceptionMapper {

	public EndCommentExceptionMapper() {
		add(new NoViableAltHandler(Messages.LABEL_ENDCOMMENT,
				Messages.VIABLE_ENDCOMMENT));
		add(new MismatchedTokenHandler(Messages.LABEL_ENDCOMMENT));
		add(new MissingTokenHandler(Messages.LABEL_ENDCOMMENT));
	}
}
