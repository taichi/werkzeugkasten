package twowaysql.model.impl;

import twowaysql.TwowaySQLContext;
import twowaysql.model.ConditionalNode;

public class AbstractConditionalNode extends AbstractNode implements
		ConditionalNode {

	protected ConditionalNode next;

	@Override
	public void add(ConditionalNode next) {
		this.next = next;
	}

	protected void execute(TwowaySQLContext context, boolean is) {
		if (is) {
			super.execute(context);
		} else {
			this.next.execute(context);
		}
	}
}
