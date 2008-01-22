package aa.bb.cc.dao;

import com.google.werkzeugkasten.core.dao.Dao;
import com.google.werkzeugkasten.core.dao.jdbc.JdbcDaoContext;
import com.google.werkzeugkasten.core.dao.jdbc.DataSourceChain.DataSource;

@Dao(context = JdbcDaoContext.class)
@DataSource
public interface EmpDao {

	int insert(Emp e);

	Emp select(int id);
}
