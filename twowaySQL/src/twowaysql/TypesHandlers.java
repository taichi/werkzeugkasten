package twowaysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

public class TypesHandlers {

	protected static Map<Integer, TypeHandler> handlers;

	public interface TypeHandler {
		<T> T get(ResultSet rs, String name);

		<T> void bind(PreparedStatement ps, int index, T value);
	}

	public Object handle(ResultSet rs, String name) {
		return handle(rs, name, Types.OTHER);
	}

	public <T> T handle(ResultSet rs, String name, int type) {
		if (rs != null) {
			TypeHandler th = getHandler(type);
			if (th != null) {
				return th.<T> get(rs, name);
			}
		}
		return null;
	}

	public <T> void handle(PreparedStatement ps, int index, T value) {
		handle(ps, index, Types.OTHER, value);
	}

	public <T> void handle(PreparedStatement ps, int index, int type, T value) {
		try {
			if (value != null) {
				TypeHandler th = getHandler(type);
				if (th != null) {
					th.<T> bind(ps, index, value);
					return;
				}
			}
			ps.setNull(index, type);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	public static void initialize() {
	}

	public static void dispose() {
	}

	public static void register(int type, TypeHandler handler) {

	}

	protected TypeHandler getHandler(int type) {
		return handlers.get(type);
	}
}