package org.handwerkszeug.chain;

public interface Chain<CTX> {

	ChainResult execute(CTX context);
}
