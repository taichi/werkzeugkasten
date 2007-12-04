package com.google.werkzeugkasten.util;

public class ClassUtil {

	@SuppressWarnings("unchecked")
	public static <T> T as(Class<T> clazz, Object obj) {
		if (obj != null && clazz.isInstance(obj)) {
			return (T) obj;
		}
		return null;
	}
}
