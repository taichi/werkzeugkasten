package werkzeugkasten.core;

public interface ChainBuilder<T, CTX extends ChainContext<T>> {

	CTX build();
}
