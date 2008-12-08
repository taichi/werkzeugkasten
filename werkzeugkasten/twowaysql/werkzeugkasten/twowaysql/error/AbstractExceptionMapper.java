package werkzeugkasten.twowaysql.error;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;

public abstract class AbstractExceptionMapper implements ExceptionMapper {

	protected Map<Class<? extends RecognitionException>, RecognitionExceptionHandler> map = new HashMap<Class<? extends RecognitionException>, RecognitionExceptionHandler>();

	protected void add(RecognitionExceptionHandler... handlers) {
		for (RecognitionExceptionHandler h : handlers) {
			this.map.put(h.getHadleType(), h);
		}
	}

	public QueryProblem map(RecognitionException ex) {
		RecognitionExceptionHandler h = this.map.get(ex.getClass());
		if (h != null) {
			return h.handle(ex);
		}
		return new RecognitionExceptionAdapter(ex);
	}
}
