package org.handwerkszeug.util;

public class EnumUtil {

	public static <E extends Enum<E> & VariableEnum> E find(E[] values,
			int value) {
		for (E e : values) {
			if (e.value() == value) {
				return e;
			}
		}
		throw new IllegalArgumentException("value=" + value);
	}
}
