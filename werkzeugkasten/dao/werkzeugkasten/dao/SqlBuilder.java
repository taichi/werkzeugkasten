package werkzeugkasten.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import werkzeugkasten.dao.BinderFactory.Binder;

public abstract class SqlBuilder<R> extends SqlExecutor.StatementHandler<R> {

	protected StringBuilder stb = new StringBuilder();

	protected List<Binder> binders = new ArrayList<Binder>();

	public void add(String part) {
		this.stb.append(part);
	}

	public <T> void add(String part, BinderFactory<T> factory, T... values) {
		this.stb.append(part);
		for (T t : values) {
			this.binders.add(factory.binder(t));
		}
	}

	@Override
	public String getSql() {
		return this.stb.toString();
	}

	@Override
	public void bind(PreparedStatement ps) throws SQLException {
		int index = 1;
		for (Binder b : this.binders) {
			b.bind(ps, index++);
		}
	}

}
