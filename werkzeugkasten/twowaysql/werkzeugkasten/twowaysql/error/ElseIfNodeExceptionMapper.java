package werkzeugkasten.twowaysql.error;

public class ElseIfNodeExceptionMapper extends AbstractExceptionMapper {

	public ElseIfNodeExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_ELSEIFNODE,
				Messages.REQUIRED_ELSEIFNODE));

	}
}
