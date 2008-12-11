package werkzeugkasten.twowaysql.dao;

public interface TwoWaySqlContext<EC> {

	void setExpressionContext();

	EC getExpressionContext();

	void append(String partOfQuery);

	String getSql();

	void add(Binder binder);

	Iterable<Binder> getBinders();
}
