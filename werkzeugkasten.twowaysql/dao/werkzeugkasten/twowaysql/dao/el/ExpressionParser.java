package werkzeugkasten.twowaysql.dao.el;

public interface ExpressionParser {

	<C> Expression<C> parse(String source);
}
