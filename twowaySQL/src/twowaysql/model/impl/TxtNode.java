package twowaysql.model.impl;

import twowaysql.TwowaySQLContext;

public class TxtNode extends AbstractNode {

	protected StringBuilder txt = new StringBuilder();

	public TxtNode() {
	}

	public void append(CharSequence txt) {
		this.txt.append(txt);
	}

	@Override
	public void execute(TwowaySQLContext context) {
		context.append(this.txt);
	}
}
