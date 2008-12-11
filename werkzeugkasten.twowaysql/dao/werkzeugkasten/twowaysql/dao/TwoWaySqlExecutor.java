package werkzeugkasten.twowaysql.dao;

public interface TwoWaySqlExecutor {

	<EC, C extends TwoWaySqlContext<EC>> void execute(C context)
			throws SQLRuntimeException;

	<EC, C extends TwoWaySqlContext<EC>, R> R execute(C context,
			ResultSetHandler<R> handler) throws SQLRuntimeException;

}
