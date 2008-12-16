package werkzeugkasten.twowaysql.dao.base;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.sql.Ref;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

import werkzeugkasten.twowaysql.dao.BinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.RefBinderFactory;
import werkzeugkasten.twowaysql.dao.base.binder.UtilDateBinderFactory;

public class DefaultBinderProducerTest {

	@Test
	public void testFind() {
		DefaultBinderProducer dbp = new DefaultBinderProducer();
		dbp.initialize();

		BinderFactory b = dbp.findByType(MyDate.class);
		assertEquals(UtilDateBinderFactory.class, b.getClass());
		b = dbp.findByType(MyRef.class);
		assertEquals(RefBinderFactory.class, b.getClass());
	}

	static class MyDate extends Date implements Serializable {
		private static final long serialVersionUID = 1L;
	}

	static class MyRef implements Ref {

		@Override
		public String getBaseTypeName() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getObject() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getObject(Map<String, Class<?>> map) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setObject(Object value) throws SQLException {
			// TODO Auto-generated method stub

		}

	}
}
