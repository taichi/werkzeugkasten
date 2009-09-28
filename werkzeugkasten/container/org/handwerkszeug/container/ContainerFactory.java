package org.handwerkszeug.container;

public interface ContainerFactory<CTX, N extends Nameable, K> extends
		Factory<Container<N, K>, CTX> {

}
