package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.error.handler.NoViableAltHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class BeginCommentExceptionMapper extends AbstractExceptionMapper {

	public BeginCommentExceptionMapper() {
		add(new NoViableAltHandler(Messages.LABEL_BEGINCOMMENT,
				Messages.VIABLE_BEGINCOMMENT));
		add(new MismatchedTokenHandler(Messages.LABEL_BEGINCOMMENT));
		add(new MissingTokenHandler(Messages.LABEL_BEGINCOMMENT));
		add(new EarlyExitHandler(Messages.LABEL_BEGINCOMMENT,
				Messages.REQUIRED_BEGINCOMMENT));
	}
}
