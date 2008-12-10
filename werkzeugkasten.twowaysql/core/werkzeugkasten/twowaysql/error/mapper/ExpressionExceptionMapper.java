package werkzeugkasten.twowaysql.error.mapper;

import werkzeugkasten.twowaysql.error.handler.EarlyExitHandler;
import werkzeugkasten.twowaysql.nls.Messages;

public class ExpressionExceptionMapper extends AbstractExceptionMapper {

	public ExpressionExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_EXPRESSION,
				Messages.REQUIRED_EXPRESSION));
	}
}
