package werkzeugkasten.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface BinderFactory<T> {

	interface Binder {
		void bind(PreparedStatement ps, int index) throws SQLException;
	}

	Binder binder(T value);

	BinderFactory<String> STRING = new BinderFactory<String>() {
		@Override
		public Binder binder(final String value) {
			return new Binder() {
				@Override
				public void bind(PreparedStatement ps, int index)
						throws SQLException {
					ps.setString(index, value);
				}
			};
		}
	};
}
