package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class LineCommentExceptionMapper extends AbstractExceptionMapper {

	public LineCommentExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_LINECOMMENT,
				Messages.REQUIRED_LINECOMMENT));
		add(new MissingTokenHandler(Messages.LABEL_LINECOMMENT));
	}
}
