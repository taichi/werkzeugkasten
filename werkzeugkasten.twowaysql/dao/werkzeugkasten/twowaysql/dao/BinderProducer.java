package werkzeugkasten.twowaysql.dao;

import werkzeugkasten.twowaysql.tree.BindNode;

public interface BinderProducer {

	<EC> Binder produce(SqlContext<EC> context, BindNode node, Object object);

}
// TODO implement candidate is
// object type based
// binder name based
// modify bind comment specification. /*?binername? expression */'hoge'
