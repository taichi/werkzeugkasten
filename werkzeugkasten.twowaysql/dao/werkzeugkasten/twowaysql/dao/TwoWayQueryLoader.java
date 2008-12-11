package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.tree.TwoWayQuery;

public interface TwoWayQueryLoader {

	TwoWayQuery load(String path);
}
// TODO implement candidate is
// . ClassLoader based
// . Properties based
// . XML based
// . any other framework configuration based, maybe adaptor