package com.google.werkzeugkasten.meta;


public interface Executable<R, P> {

	R execute(P parameter);

}
