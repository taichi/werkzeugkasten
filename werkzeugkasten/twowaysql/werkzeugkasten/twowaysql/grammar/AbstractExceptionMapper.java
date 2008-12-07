package werkzeugkasten.twowaysql.grammar;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

public abstract class AbstractExceptionMapper implements ExceptionMapper {

	protected Map<Class<? extends RecognitionException>, Handler> map = new HashMap<Class<? extends RecognitionException>, Handler>();

	protected interface Handler {
		Class<? extends RecognitionException> getHadleType();

		QueryProblem handle(RecognitionException ex);
	}

	protected void add(Handler... handlers) {
		for (Handler h : handlers) {
			this.map.put(h.getHadleType(), h);
		}
	}

	public QueryProblem map(RecognitionException ex) {
		Handler h = this.map.get(ex.getClass());
		if (h != null) {
			return h.handle(ex);
		}
		return new RecognitionExceptionAdapter(ex);
	}

	protected String getTokenErrorDisplay(Token t) {
		String s = t.getText();
		if (s == null) {
			if (t.getType() == Token.EOF) {
				s = "<EOF>";
			} else {
				s = "<" + t.getType() + ">";
			}
		}
		s = s.replaceAll("\n", "\\\\n");
		s = s.replaceAll("\r", "\\\\r");
		s = s.replaceAll("\t", "\\\\t");
		return "'" + s + "'";
	}
}
