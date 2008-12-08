package werkzeugkasten.twowaysql.error;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;

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
}
