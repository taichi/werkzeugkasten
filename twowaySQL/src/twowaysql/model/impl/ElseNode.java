package twowaysql.model.impl;

import twowaysql.TwowaySQLContext;
import twowaysql.model.ConditionalNode;

public class ElseNode extends AbstractConditionalNode {

	@Override
	public void execute(TwowaySQLContext context) {
		super.execute(context, true);
		context.setConditionState(true);
	}

	@Override
	public void add(ConditionalNode next) {
	}
}
