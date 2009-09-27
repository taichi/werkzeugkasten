package org.handwerkszeug.container.reflect;

import java.lang.reflect.Method;

import org.handwerkszeug.container.Accessible;

public class MethodBasedProperty extends AbstractNameable implements Accessible {

	protected Method getter;
	protected Method setter;

	public MethodBasedProperty(String name) {
		super(name);

	}

	@Override
	public <T> T get(Object instance) {
		return RefrectionUtil.execute(this.getter, instance);
	}

	@Override
	public <T> void set(Object instance, T value) {
		RefrectionUtil.execute(this.setter, instance, value);
	}

	protected void setSetter(Method setter) {
		this.setter = setter;
	}

	protected void setGetter(Method getter) {
		this.getter = getter;
	}

}
