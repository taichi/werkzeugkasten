package werkzeugkasten.factory;

public interface Extension extends Adaptable {

	String id();

	<T extends Extension> Iterable<T> children();
}
