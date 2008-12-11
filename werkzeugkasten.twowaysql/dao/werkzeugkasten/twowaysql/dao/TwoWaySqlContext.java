package werkzeugkasten.twowaysql.dao;

public interface TwoWaySqlContext<EC> {

	EC getExpressionContext();

	ParsedTwoWayQuery getTwoWayQuery();

	void append(String partOfQuery);

	String getSql();

	void add(Binder binder);

	Iterable<Binder> getBinders();
}
