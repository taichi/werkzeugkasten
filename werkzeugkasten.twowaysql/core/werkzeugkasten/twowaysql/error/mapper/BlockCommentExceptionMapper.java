package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.error.handler.MismatchedTokenHandler;
import werkzeugkasten.twowaysql.error.handler.MissingTokenHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class BlockCommentExceptionMapper extends AbstractExceptionMapper {

	public BlockCommentExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_BLOCKCOMMENT,
				Messages.REQUIRED_BLOCKCOMMENT));
		add(new MismatchedTokenHandler(Messages.LABEL_BLOCKCOMMENT));
		add(new MissingTokenHandler(Messages.LABEL_BLOCKCOMMENT));
	}
}
