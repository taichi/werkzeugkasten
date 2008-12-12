package werkzeugkasten.twowaysql.dao;

public interface SqlContext<EC> {

	EC getExpressionContext();

	QueryWrapper getTwoWayQuery();

	void append(String partOfQuery);

	String getSql();

	void add(Binder binder);

	Iterable<Binder> getBinders();
}
