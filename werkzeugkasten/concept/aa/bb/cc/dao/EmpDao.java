package aa.bb.cc.dao;

import com.google.werkzeugkasten.core.dao.jdbc.DataSourceChain.DataSource;

@DataSource
public interface EmpDao {

	int insert(Emp e);

	Emp select(int id);
}
