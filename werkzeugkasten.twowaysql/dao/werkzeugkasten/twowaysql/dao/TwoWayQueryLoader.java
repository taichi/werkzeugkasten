package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.tree.TwoWayQuery;

public interface TwoWayQueryLoader<C> {

	TwoWayQuery load(C context);
}
// TODO implement candidate is
// . ClassLoader based
// . Properties based
// . XML based
// . any other framework configuration based, maybe adaptor