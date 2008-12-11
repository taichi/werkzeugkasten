package werkzeugkasten.twowaysql.dao.el;

public interface Expression<C> {

	String getSource();

	<R> R eval(C context);
}
