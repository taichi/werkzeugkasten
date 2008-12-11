package werkzeugkasten.twowaysql.dao;

public interface TwoWaySqlExecutor<C extends TwoWaySqlContext> {

	void execute(C context) throws SQLRuntimeException;

	<R> R execute(C context, ResultSetHandler<R> handler)
			throws SQLRuntimeException;

}
