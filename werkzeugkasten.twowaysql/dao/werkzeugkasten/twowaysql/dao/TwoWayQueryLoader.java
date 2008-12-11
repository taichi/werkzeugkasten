package werkzeugkasten.twowaysql.dao;


public interface TwoWayQueryLoader<LC> {

	ParsedTwoWayQuery load(LC context);
}
// TODO implement candidate is
// . ClassLoader based
// . Properties based
// . XML based
// . any other framework configuration based, maybe adaptor