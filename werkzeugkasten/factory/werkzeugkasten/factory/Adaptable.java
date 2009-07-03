package werkzeugkasten.factory;

public interface Adaptable {

	<T> T getAdapter(Class<T> adapterType);
}
