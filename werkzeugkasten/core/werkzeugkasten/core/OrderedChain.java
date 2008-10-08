package werkzeugkasten.core;

public interface OrderedChain<R, CTX extends ChainContext<R>> extends
		Chain<R, CTX>, Iterable<Chain<R, CTX>> {

	void add(Chain<R, CTX> c);
}
