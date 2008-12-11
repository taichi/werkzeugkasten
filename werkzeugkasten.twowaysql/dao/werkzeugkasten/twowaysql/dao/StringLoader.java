package werkzeugkasten.twowaysql.dao;

public interface StringLoader {

	String load(String path);
}
// TODO implement candidate is
// . ClassLoader based
// . Properties based
// . XML based
// . any other framework configuration based, maybe adaptor