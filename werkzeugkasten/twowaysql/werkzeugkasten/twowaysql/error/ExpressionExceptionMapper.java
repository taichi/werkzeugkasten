package werkzeugkasten.twowaysql.error;

public class ExpressionExceptionMapper extends AbstractExceptionMapper {

	public ExpressionExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_EXPRESSION,
				Messages.REQUIRED_EXPRESSION));
	}
}
