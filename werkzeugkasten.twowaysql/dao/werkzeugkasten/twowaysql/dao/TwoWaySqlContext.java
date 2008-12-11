package werkzeugkasten.twowaysql.dao;

public interface TwoWaySqlContext {

	void append(String partOfQuery);

	void add(Binder binder);

}
