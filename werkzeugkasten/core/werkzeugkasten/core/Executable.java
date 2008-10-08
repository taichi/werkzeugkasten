package werkzeugkasten.core;


public interface Executable<R, P> {

	R execute(P parameter);

}
