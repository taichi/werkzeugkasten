package aa.bb.cc.dao.autogen;

import java.sql.PreparedStatement;

import com.google.werkzeugkasten.core.dao.jdbc.DataSourceChain;
import com.google.werkzeugkasten.core.dao.jdbc.ExecuteUpdateChain;
import com.google.werkzeugkasten.core.dao.jdbc.PrepareStatementChain;
import com.google.werkzeugkasten.core.dao.jdbc.QueryChain;
import com.google.werkzeugkasten.meta.ChainBuilder;

public class EmpDao_insertBuilder implements
		ChainBuilder<Integer, EmpDaoContext<Integer>> {

	public EmpDaoContext<Integer> build() {
		EmpDaoContext<Integer> result = new EmpDaoContext<Integer>();
		result
				.add(new QueryChain<Integer, PreparedStatement, EmpDaoContext<Integer>>() {
					@Override
					protected String getQuery(EmpDaoContext<Integer> context) {
						return "INSERT INTO EMP (ID,NAME,TIMESTAMP) values (?,?,?)";
					}
				});
		result
				.add(new DataSourceChain<Integer, PreparedStatement, EmpDaoContext<Integer>>());
		result
				.add(new PrepareStatementChain<Integer, EmpDaoContext<Integer>>());
		// result.add bind chain
		result.add(new ExecuteUpdateChain<EmpDaoContext<Integer>>());
		return result;
	}
}
