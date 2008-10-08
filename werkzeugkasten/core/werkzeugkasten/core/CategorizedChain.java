package werkzeugkasten.core;

public interface CategorizedChain<R, CTX extends ChainContext<R>, C extends Category<R, CTX>>
		extends Chain<R, CTX> {

	void put(C category, Chain<R, CTX> chain);
}
