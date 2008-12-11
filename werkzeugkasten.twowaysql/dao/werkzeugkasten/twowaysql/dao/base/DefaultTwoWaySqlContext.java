package werkzeugkasten.twowaysql.dao.base;

import java.util.ArrayList;
import java.util.List;

import werkzeugkasten.twowaysql.dao.Binder;
import werkzeugkasten.twowaysql.dao.ParsedTwoWayQuery;
import werkzeugkasten.twowaysql.dao.TwoWaySqlContext;

public class DefaultTwoWaySqlContext<EC> implements TwoWaySqlContext<EC> {

	protected EC expressionContext;
	protected ParsedTwoWayQuery twoWayQuery;
	protected StringBuilder allOfQuery = new StringBuilder();
	protected List<Binder> binders = new ArrayList<Binder>();

	protected DefaultTwoWaySqlContext(EC context, ParsedTwoWayQuery twoWayQuery) {
		this.expressionContext = context;
		this.twoWayQuery = twoWayQuery;
	}

	@Override
	public EC getExpressionContext() {
		return this.expressionContext;
	}

	@Override
	public ParsedTwoWayQuery getTwoWayQuery() {
		return this.twoWayQuery;
	}

	@Override
	public void append(String partOfQuery) {
		this.allOfQuery.append(partOfQuery);
	}

	@Override
	public void add(Binder binder) {
		this.binders.add(binder);
	}

	@Override
	public String getSql() {
		return new String(this.allOfQuery);
	}

	@Override
	public Iterable<Binder> getBinders() {
		return this.binders;
	}

}
