package werkzeugkasten.core;

public interface Chain<R, CTX extends ChainContext<R>> extends
		Executable<R, CTX> {

}
