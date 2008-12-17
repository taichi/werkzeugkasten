package werkzeugkasten.twowaysql.dao.base;

import java.util.ArrayList;
import java.util.List;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.QueryWrapper;
import werkzeugkasten.twowaysql.dao.SqlContext;

public class DefaultSqlContext<EC> implements SqlContext<EC> {

	protected EC expressionContext;
	protected QueryWrapper twoWayQuery;
	protected List<QueryStackElement> queryStack = new ArrayList<QueryStackElement>();
	protected List<Binder> binders = new ArrayList<Binder>();

	protected static class QueryStackElement {
		protected StringBuilder structured = new StringBuilder();
		protected boolean concluded = false;
	}

	protected DefaultSqlContext(EC context, QueryWrapper twoWayQuery) {
		this.expressionContext = context;
		this.twoWayQuery = twoWayQuery;
		QueryStackElement e = new QueryStackElement();
		e.structured = new StringBuilder();
		this.queryStack.add(e);
	}

	@Override
	public EC getExpressionContext() {
		return this.expressionContext;
	}

	@Override
	public QueryWrapper getTwoWayQuery() {
		return this.twoWayQuery;
	}

	@Override
	public void begin() {
		QueryStackElement e = new QueryStackElement();
		this.queryStack.add(e);
	}

	protected QueryStackElement getLast() {
		int last = this.queryStack.size() - 1;
		return this.queryStack.get(last);
	}

	@Override
	public void conclude() {
		QueryStackElement e = getLast();
		e.concluded = true;
	}

	@Override
	public boolean isConcluded() {
		return getLast().concluded;
	}

	@Override
	public void end() {
		QueryStackElement c = this.queryStack
				.remove(this.queryStack.size() - 1);
		QueryStackElement p = getLast();
		if (c.concluded) {
			p.structured.append(c.structured);
		}
	}

	@Override
	public void append(String partOfQuery) {
		getLast().structured.append(partOfQuery);
	}

	@Override
	public void add(Binder binder) {
		this.binders.add(binder);
	}

	@Override
	public String getSql() {
		return new String(getLast().structured);
	}

	@Override
	public Iterable<Binder> getBinders() {
		return this.binders;
	}

}
