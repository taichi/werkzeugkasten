package twowaysql.model.impl;

import twowaysql.TwowaySQLContext;

public class BeginNode extends AbstractNode {

	@Override
	public void execute(TwowaySQLContext context) {
		StringBuilder stb = new StringBuilder(context.getQuery());
		super.execute(context);
		if (context.getConditionState() == false) {
			context.setQuery(stb);
		}
	}
}
