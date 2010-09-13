package org.handwerkszeug.chain;

public interface ChainExecutor<CTX, R extends ChainResult> {

	ChainResult execute(CTX context);
}
