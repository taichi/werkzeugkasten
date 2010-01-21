package org.handwerkszeug.kvsview.model.pb;

import java.util.UUID;

public class UUIDKeyGenerator implements KeyGenerator {

	@Override
	public String nextKey() {
		UUID id = UUID.randomUUID();
		return id.toString();
	}

	@Override
	public String nextKey(ModelPB.Node node) {
		return nextKey();
	}
}
