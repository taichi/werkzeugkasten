package org.handwerkszeug.util;

public class Validation {

	public static void notNull(Object o, String name) {
		if (o == null) {
			throw new IllegalArgumentException(name);
		}
	}
}
