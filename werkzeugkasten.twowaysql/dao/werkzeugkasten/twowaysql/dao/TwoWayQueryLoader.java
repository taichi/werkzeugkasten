package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.tree.TwoWayQuery;

public interface TwoWayQueryLoader<LC> {

	TwoWayQuery load(LC context);
}
// TODO implement candidate is
// . ClassLoader based
// . Properties based
// . XML based
// . any other framework configuration based, maybe adaptor