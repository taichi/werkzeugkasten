package org.handwerkszeug.container.reflect;

import org.handwerkszeug.container.Acceptor;
import org.handwerkszeug.container.Accessible;
import org.handwerkszeug.container.Container;
import org.handwerkszeug.container._;

public class RefrectiveClass extends AbstractContainer<Accessible, String>
		implements Container<Accessible, String> {

	protected Class<?> target;
	protected Module module;

	public RefrectiveClass(Module module, Class<?> clazz) {
		super(clazz.getName());
		this.target = clazz;
		this.module = module;
	}

	@Override
	public void walk(final Acceptor<Accessible, Boolean> walker) {
		if (internalWalk(walker)) {
			this.module.accept(this.target.getSuperclass(),
					new Acceptor<RefrectiveClass, _>() {
						@Override
						public _ accept(RefrectiveClass content) {
							content.walk(walker);
							return _._;
						}
					}, null);
		}
	}

}
