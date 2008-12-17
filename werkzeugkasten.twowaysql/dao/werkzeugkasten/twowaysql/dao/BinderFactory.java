package werkzeugkasten.twowaysql.dao;

public interface BinderFactory {

	Class<?> targetType();

	String bindingTypeName();

	Binder create(Object value) throws IllegalArgumentException;

}
