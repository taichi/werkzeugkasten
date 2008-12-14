package werkzeugkasten.twowaysql.dao;

public interface SqlContext<EC> {

	EC getExpressionContext();

	QueryWrapper getTwoWayQuery();

	void begin();

	void conclude();

	void append(String partOfQuery);

	void end();

	String getSql();

	void add(Binder binder);

	Iterable<Binder> getBinders();
}
