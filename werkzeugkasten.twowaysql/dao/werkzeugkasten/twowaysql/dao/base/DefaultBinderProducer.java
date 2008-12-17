package werkzeugkasten.twowaysql.dao.base;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;
import werkzeugkasten.twowaysql.dao.BinderProducer;
import werkzeugkasten.twowaysql.dao.SqlContext;
import werkzeugkasten.twowaysql.dao.base.binder.ArrayBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.AsciiStreamBinderFactory;
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
import werkzeugkasten.twowaysql.dao.base.binder.NCharacterStreamBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.NClobBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.NStringBinderFactory;
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
import werkzeugkasten.twowaysql.nls.Messages;
import werkzeugkasten.twowaysql.tree.BindNode;
import werkzeugkasten.twowaysql.tree.ExpressionNode;
import werkzeugkasten.twowaysql.tree.TxtNode;
import werkzeugkasten.twowaysql.tree.loc.TextLocationUtil;

public class DefaultBinderProducer implements BinderProducer {

	protected static final Logger LOG = LoggerFactory
			.getLogger(DefaultBinderProducer.class);

	protected Map<Class<?>, BinderFactory> typeRegistory = new HashMap<Class<?>, BinderFactory>();
	protected Map<String, BinderFactory> nameRegistory = new HashMap<String, BinderFactory>();

	public void initialize() {
		registerAll(new ArrayBinderFactory());
		registerAll(new BigDecimalBinderFactory());
		registerAll(new BinaryStreamBinderFactory());
		registerAll(new BlobBinderFactory());
		registerAll(new BooleanBinderFactory());
		registerAll(new ByteBinderFactory());
		registerAll(new BytesBinderFactory());
		registerAll(new CharacterStreamBinderFactory());
		registerAll(new ClobBinderFactory());
		registerAll(new DateBinderFactory());
		registerAll(new DoubleBinderFactory());
		registerAll(new FloatBinderFactory());
		registerAll(new NClobBinderFactory());
		registerAll(new ObjectBinderFactory());
		registerAll(new RefBinderFactory());
		registerAll(new RowIdBinderFactory());
		registerAll(new ShortBinderFactory());
		registerAll(new SQLXMLBinderFactory());
		registerAll(new StringBinderFactory());
		registerAll(new TimeBinderFactory());
		registerAll(new TimestampBinderFactory());
		registerAll(new URLBinderFactory());
		registerAll(new UtilDateBinderFactory());

		registerByName(new AsciiStreamBinderFactory());
		registerByName(new NCharacterStreamBinderFactory());
		registerByName(new NStringBinderFactory());
	}

	public void registerAll(BinderFactory factory) {
		registerByType(factory);
		registerByName(factory);
	}

	public void registerByType(BinderFactory factory) {
		this.typeRegistory.put(factory.targetType(), factory);
	}

	public void registerByName(BinderFactory factory) {
		this.nameRegistory
				.put(factory.bindingTypeName().toLowerCase(), factory);
	}

	protected BinderFactory findByType(Class<?> clazz) {
		BinderFactory factory = null;
		loop: while (factory == null) {
			factory = this.typeRegistory.get(clazz);
			if (factory == null) {
				Class<?>[] ary = clazz.getInterfaces();
				if (ary != null) {
					for (Class<?> c : ary) {
						factory = findByType(c);
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

	protected BinderFactory findByName(String name) {
		return this.nameRegistory.get(name.toLowerCase());
	}

	@Override
	public <EC> Binder produce(SqlContext<EC> context, BindNode node,
			Object object) {
		String binderName = "";
		try {
			if (object == null) {
				throw new IllegalArgumentException();
			}
			BinderFactory factory = null;
			TxtNode nameNode = node.getBindingName();
			if (nameNode != null) {
				String src = context.getTwoWayQuery().getSource();
				binderName = TextLocationUtil.substring(src, nameNode);
				factory = findByName(binderName);
			}
			if (factory == null) {
				factory = findByType(object.getClass());
			}
			if (factory == null) {
				throw new IllegalStateException();
			}
			return factory.create(object);
		} catch (RuntimeException e) {
			if (LOG.isDebugEnabled()) {
				ExpressionNode en = node.getExpression();
				String exp = TextLocationUtil.substring(context
						.getTwoWayQuery().getSource(), en);
				LOG.debug(String.format(Messages.BINDERFACTORY_FAILED,
						binderName, exp, object));
			}
			throw e;
		}
	}
}
