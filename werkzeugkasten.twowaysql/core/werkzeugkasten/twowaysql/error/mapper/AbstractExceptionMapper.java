package werkzeugkasten.twowaysql.error.mapper;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;

import werkzeugkasten.twowaysql.error.ExceptionMapper;
import werkzeugkasten.twowaysql.error.QueryProblem;
import werkzeugkasten.twowaysql.error.RecognitionExceptionAdapter;
import werkzeugkasten.twowaysql.error.RecognitionExceptionHandler;

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
