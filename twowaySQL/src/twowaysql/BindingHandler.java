package twowaysql;

import java.sql.PreparedStatement;

public interface BindingHandler {

	void bind(PreparedStatement ps, int index);
}