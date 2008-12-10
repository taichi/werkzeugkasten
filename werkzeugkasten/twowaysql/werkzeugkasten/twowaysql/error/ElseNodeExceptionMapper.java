package werkzeugkasten.twowaysql.error;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class ElseNodeExceptionMapper extends AbstractExceptionMapper {

	public ElseNodeExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_ELSENODE,
				Messages.REQUIRED_ELSENODE));

	}
}
