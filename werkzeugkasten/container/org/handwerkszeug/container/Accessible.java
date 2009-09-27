package org.handwerkszeug.container;

// getter,setter pare
// field
// property method pare. cf. String name(); void name(String value);
public interface Accessible extends Nameable {

	<T> void set(Object instance, T value);

	<T> T get(Object instance);
}
