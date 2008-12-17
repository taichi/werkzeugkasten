package werkzeugkasten.twowaysql.dao.base;

import java.util.ArrayList;
import java.util.List;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.QueryWrapper;
import werkzeugkasten.twowaysql.dao.SqlContext;

public class DefaultSqlContext<EC> implements SqlContext<EC> {

	protected EC expressionContext;
	protected QueryWrapper twoWayQuery;
	protected List<BeginStackElement> beginStack = new ArrayList<BeginStackElement>();
	protected List<Binder> binders = new ArrayList<Binder>();

	protected static class BeginStackElement {
		protected StringBuilder structured = new StringBuilder();
		protected boolean concluded = false;
	}

	protected DefaultSqlContext(EC context, QueryWrapper twoWayQuery) {
		this.expressionContext = context;
		this.twoWayQuery = twoWayQuery;
		BeginStackElement e = new BeginStackElement();
		e.structured = new StringBuilder();
		this.beginStack.add(e);
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
		BeginStackElement e = new BeginStackElement();
		this.beginStack.add(e);
	}

	protected BeginStackElement getLast() {
		int last = this.beginStack.size() - 1;
		return this.beginStack.get(last);
	}

	@Override
	public void conclude() {
		BeginStackElement e = getLast();
		e.concluded = true;
	}

	@Override
	public boolean isConcluded() {
		return getLast().concluded;
	}

	@Override
	public void end() {
		BeginStackElement c = this.beginStack
				.remove(this.beginStack.size() - 1);
		BeginStackElement p = getLast();
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
