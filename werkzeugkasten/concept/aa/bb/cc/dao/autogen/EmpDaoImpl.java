package aa.bb.cc.dao.autogen;

import aa.bb.cc.dao.Emp;
import aa.bb.cc.dao.EmpDao;

public class EmpDaoImpl implements EmpDao {

	public Emp select(int id) {
		Emp e = new Emp();
		return select(e);
	}

	protected Emp select(Emp e) {
		return null;
	}

	public void insert(Emp e) {
		EmpDao_insertBuilder builder = new EmpDao_insertBuilder();
		EmpDaoContext<Integer> ctx = builder.build();
		ctx.initialize(e);
		// SQL
		// getConnection
		// prepareStmt
		// bind
		// executeQuery
		// mapping
		ctx.execute();
	}
}
