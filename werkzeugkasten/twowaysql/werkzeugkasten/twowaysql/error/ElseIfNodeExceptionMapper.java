package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class ElseIfNodeExceptionMapper extends AbstractExceptionMapper {

	public ElseIfNodeExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_ELSEIFNODE,
				Messages.REQUIRED_ELSEIFNODE));

	}
}
