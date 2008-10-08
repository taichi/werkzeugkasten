package werkzeugkasten.core;

public interface Category<R, CTX extends ChainContext<R>> extends
		Executable<R, CTX> {

	boolean match(CTX context);
}
