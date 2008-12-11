package werkzeugkasten.twowaysql.dao;

public interface TwoWaySqlContext<EC> {

	EC getExpressionContext();

	TwoWayQueryWrapper getTwoWayQuery();

	void append(String partOfQuery);

	String getSql();

	void add(Binder binder);

	Iterable<Binder> getBinders();
}
