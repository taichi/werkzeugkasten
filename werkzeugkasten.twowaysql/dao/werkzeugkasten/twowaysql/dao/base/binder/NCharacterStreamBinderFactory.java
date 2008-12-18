package werkzeugkasten.twowaysql.dao.base.binder;

import java.io.BufferedReader;
import java.io.Reader;

import werkzeugkasten.common.util.ConverterUtil;
import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.BinderFactory;

public class NCharacterStreamBinderFactory implements BinderFactory {

	@Override
	public Binder create(Object value) throws IllegalArgumentException {
		if (value instanceof Reader) {
			Reader v = (Reader) value;
			return new NCharacterStreamBinder(v);
		}
		Reader v = ConverterUtil.convert(value, Reader.class);
		if (v != null) {
			return new NCharacterStreamBinder(new BufferedReader(v));
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Class<?> targetType() {
		return Reader.class;
	}

	@Override
	public String bindingTypeName() {
		return "NCharacterStream";
	}
}
