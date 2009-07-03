package werkzeugkasten.factory;

public interface AdapterManager {

	<T> T getAdapter(Object adaptable, Class<T> adapterType);

	<T> void register(AdapterFactory<T> factory, Class<T> atapterType);

	<T> void unregister(AdapterFactory<T> factory);

	<T> void unregister(AdapterFactory<T> factory, Class<T> atapterType);
}
