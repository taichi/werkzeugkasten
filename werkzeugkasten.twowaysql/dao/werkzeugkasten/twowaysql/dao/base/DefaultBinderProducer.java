package werkzeugkasten.twowaysql.dao.base;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderProducer;
import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.tree.BindNode;

public class DefaultBinderProducer implements BinderProducer {

	@Override
	public <EC> Binder produce(SqlContext<EC> context, BindNode node,
			Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
