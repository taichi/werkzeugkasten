package werkzeugkasten.twowaysql.error;

public class TxtExceptionMapper extends AbstractExceptionMapper {

	public TxtExceptionMapper() {
		add(new EarlyExitHandler(Messages.LABEL_TXT, Messages.REQUIRED_TXT));
	}
}
