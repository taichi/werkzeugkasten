package werkzeugkasten.twowaysql.error;

public class EndCommentExceptionMapper extends AbstractExceptionMapper {

	public EndCommentExceptionMapper() {
		add(new NoViableAltHandler(Messages.LABEL_ENDCOMMENT,
				Messages.VIABLE_ENDCOMMENT));
	}
}
