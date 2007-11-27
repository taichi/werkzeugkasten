package com.google.werkzeugkasten.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringLoader {

	private static final Logger logger = Logger.getLogger(StringLoader.class
			.getName());

	public static void load(Class<?> holder) {
		load(holder, holder.getName());
	}

	public static void load(Class<?> holder, String name) {
		ResourceBundle rb = getBundle(name, holder.getClassLoader());
		if (rb == null) {
			return;
		}
		Field[] fields = holder.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (validateMask(field)) {
				continue;
			}
			String key = field.getName();
			if (rb.containsKey(key) == false) {
				logger.log(Level.WARNING, key + " not found in " + name);
			}
			String msg = rb.getString(key);
			try {
				if (isAssignableFrom(String.class, field)) {
					field.set(null, msg);
				}
			} catch (Exception e) {
				logger.log(Level.ALL, e.getMessage(), e);
			}
		}
	}

	private static boolean validateMask(Field f) {
		final int MOD_EXPECTED = Modifier.PUBLIC | Modifier.STATIC;
		final int MOD_MASK = MOD_EXPECTED | Modifier.FINAL;
		return (f.getModifiers() & MOD_MASK) != MOD_EXPECTED;
	}

	private static ResourceBundle getBundle(String name, ClassLoader loader) {
		try {
			return ResourceBundle.getBundle(name, Locale.getDefault(), loader);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	private static boolean isAssignableFrom(final Class<?> clazz,
			final Field target) {
		return clazz.isAssignableFrom(target.getType());
	}
}
