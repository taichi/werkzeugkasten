package werkzeugkasten.twowaysql.dao.base;

import java.util.HashMap;
import java.util.Map;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;
import werkzeugkasten.twowaysql.dao.BinderProducer;
import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.dao.base.binder.ArrayBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.BigDecimalBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.BinaryStreamBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.BlobBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.BooleanBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.ByteBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.BytesBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.CharacterStreamBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.ClobBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.DateBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.DoubleBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.FloatBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.NClobBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.ObjectBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.RefBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.RowIdBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.SQLXMLBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.ShortBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.StringBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.TimeBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.TimestampBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.URLBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.UtilDateBinderFactory;
import werkzeugkasten.twowaysql.tree.BindNode;

public class DefaultBinderProducer implements BinderProducer {

	protected Map<Class<?>, BinderFactory> factories = new HashMap<Class<?>, BinderFactory>();

	public void initialize() {
		register(new ArrayBinderFactory());
		register(new BigDecimalBinderFactory());
		register(new BinaryStreamBinderFactory());
		register(new BlobBinderFactory());
		register(new BooleanBinderFactory());
		register(new ByteBinderFactory());
		register(new BytesBinderFactory());
		register(new CharacterStreamBinderFactory());
		register(new ClobBinderFactory());
		register(new DateBinderFactory());
		register(new DoubleBinderFactory());
		register(new FloatBinderFactory());
		register(new NClobBinderFactory());
		register(new ObjectBinderFactory());
		register(new RefBinderFactory());
		register(new RowIdBinderFactory());
		register(new ShortBinderFactory());
		register(new SQLXMLBinderFactory());
		register(new StringBinderFactory());
		register(new TimeBinderFactory());
		register(new TimestampBinderFactory());
		register(new URLBinderFactory());
		register(new UtilDateBinderFactory());
	}

	public void register(BinderFactory factory) {
		this.factories.put(factory.targetType(), factory);
	}

	protected BinderFactory find(Class<?> clazz) {
		BinderFactory factory = null;
		loop: while (factory == null) {
			factory = this.factories.get(clazz);
			if (factory == null) {
				Class<?>[] ary = clazz.getInterfaces();
				if (ary != null) {
					for (Class<?> c : ary) {
						factory = find(c);
						if (factory != null) {
							break loop;
						}
					}
				}
				Class<?> parent = clazz.getSuperclass();
				if (parent == null) {
					return null;
				}
				clazz = parent;
			}
		}
		return factory;
	}

	@Override
	public <EC> Binder produce(SqlContext<EC> context, BindNode node,
			Object object) {
		if (object == null) {
			throw new IllegalArgumentException();
		}
		BinderFactory factory = find(object.getClass());
		return factory.create(object);
	}
}
