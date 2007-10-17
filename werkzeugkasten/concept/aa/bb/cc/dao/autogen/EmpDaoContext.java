/**
 * 
 */
package aa.bb.cc.dao.autogen;

import java.sql.PreparedStatement;

import aa.bb.cc.dao.Emp;

import com.google.werkzeugkasten.core.dao.jdbc.JdbcDaoContext;
import com.google.werkzeugkasten.meta.Initializable.Initialize;

class EmpDaoContext<R> extends
		JdbcDaoContext<R, PreparedStatement, EmpDaoContext<R>> {
	protected Emp emp;

	@Initialize
	void initialize(Emp emp) {
		this.emp = emp;
	}
}