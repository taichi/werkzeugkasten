package twowaysql.model.impl;

import twowaysql.BindingHandler;
import twowaysql.TwowaySQLContext;
import twowaysql.el.ELBindingHandler;

public class BindNode extends AbstractNode {

	protected BindingHandler handler;

	public BindNode(String source) {
		this.handler = new ELBindingHandler(source);
	}

	@Override
	public void execute(TwowaySQLContext context) {
		context.addBinder(handler);
	}
}
