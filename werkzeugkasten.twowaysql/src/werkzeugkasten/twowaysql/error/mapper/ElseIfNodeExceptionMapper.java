package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class ElseIfNodeExceptionMapper extends AbstractExceptionMapper {

	public ElseIfNodeExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_ELSEIFNODE,
				Messages.REQUIRED_ELSEIFNODE));

	}
}
