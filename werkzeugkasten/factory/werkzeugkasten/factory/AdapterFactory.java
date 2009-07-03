package werkzeugkasten.factory;

public interface AdapterFactory<T> {

	T getAdapter(Object adaptable, Class<T> adapterType);
}
