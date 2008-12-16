package werkzeugkasten.twowaysql.dao.base.binder;

import java.util.Date;

public class UtilDateBinderFactory extends DateBinderFactory {

	@Override
	public Class<?> targetType() {
		return Date.class;
	}

}
