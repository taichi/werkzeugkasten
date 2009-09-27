package org.handwerkszeug.container;

public interface ContainerFactory<CTX, N extends Nameable, K> {

	Container<N, K> create(CTX context);
}
