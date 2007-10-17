package com.google.werkzeugkasten.meta;

public interface ChainBuilder<T, CTX extends ChainContext<T>> {

	CTX build();
}
