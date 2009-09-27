package org.handwerkszeug.container.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.handwerkszeug.container.Acceptor;
import org.handwerkszeug.container._;

public class RefrectionUtil {

	public interface RefrectiveAction<R> {
		R execute() throws IllegalAccessException, InvocationTargetException;
	}

	public static <R> R execute(RefrectiveAction<R> action) {
		try {
			return action.execute();
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getCause());
		}
	}

	public static <R> R execute(final Method method, final Object instance,
			final Object... params) {
		return execute(new RefrectiveAction<R>() {
			@SuppressWarnings("unchecked")
			@Override
			public R execute() throws IllegalAccessException,
					InvocationTargetException {
				return (R) method.invoke(instance, params);
			}
		});
	}

	public static <R> R get(final Field field, final Object instance) {
		return execute(new RefrectiveAction<R>() {
			@SuppressWarnings("unchecked")
			@Override
			public R execute() throws IllegalAccessException,
					InvocationTargetException {
				return (R) field.get(instance);
			}
		});
	}

	public static void set(final Field field, final Object instance,
			final Object value) {
		execute(new RefrectiveAction<Void>() {
			@Override
			public Void execute() throws IllegalAccessException,
					InvocationTargetException {
				field.set(instance, value);
				return null;
			}
		});
	}

	public static <C extends AccessibleObject> void walk(C[] objects,
			Acceptor<C, _> walker) {
		for (C ao : objects) {
			walker.accept(ao);
		}
	}

	public static void walkField(final Class<?> clazz,
			final Acceptor<Field, _> walker) {
		walk(clazz.getDeclaredFields(), walker);
	}

	public static void walkMethod(final Class<?> clazz,
			final Acceptor<Method, _> walker) {
		walk(clazz.getDeclaredMethods(), walker);
	}

}
