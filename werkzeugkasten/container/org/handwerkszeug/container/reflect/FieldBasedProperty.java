package org.handwerkszeug.container.reflect;

import java.lang.reflect.Field;

import org.handwerkszeug.container.Accessible;

public class FieldBasedProperty extends AbstractNameable implements Accessible {

	protected Field field;

	public FieldBasedProperty(Field field) {
		super(field.getName());
		this.field = field;
	}

	@Override
	public <T> T get(Object instance) {
		return RefrectionUtil.get(this.field, instance);
	}

	@Override
	public <T> void set(Object instance, T value) {
		RefrectionUtil.set(this.field, instance, value);
	}

}
