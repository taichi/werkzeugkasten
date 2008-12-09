package werkzeugkasten.twowaysql.error;

public class ElseNodeExceptionMapper extends AbstractExceptionMapper {

	public ElseNodeExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_ELSENODE,
				Messages.REQUIRED_ELSENODE));

	}
}
