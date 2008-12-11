package werkzeugkasten.twowaysql.dao;

public interface TwoWaySqlContext<EC> {

	void setExpressionContext(EC context);

	EC getExpressionContext();

	void append(String partOfQuery);

	String getSql();

	void add(Binder binder);

	Iterable<Binder> getBinders();
}
