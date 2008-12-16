package werkzeugkasten.twowaysql.dao.base;

import java.util.HashMap;
import java.util.Map;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;
import werkzeugkasten.twowaysql.dao.BinderProducer;
import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.tree.BindNode;

public class DefaultBinderProducer implements BinderProducer {

	protected Map<Class<?>, BinderFactory> factories = new HashMap<Class<?>, BinderFactory>();

	public void initialize() {

	}

	public void register(BinderFactory factory) {
		this.factories.put(factory.targetType(), factory);
	}

	@Override
	public <EC> Binder produce(SqlContext<EC> context, BindNode node,
			Object object) {
		if (object == null) {
			throw new IllegalArgumentException();
		}
		BinderFactory factory = this.factories.get(object.getClass());
		return factory.create(object);
	}
}
