package org.handwerkszeug.kvsview.model.pb;

import java.util.UUID;

public class UUIDKeyGenerator {

	public String nextKey(ModelPB.Node node) {
		// TODO より低コストかつ、ノードの状態を考慮したID生成処理を実装しる。
		UUID id = UUID.randomUUID();
		return id.toString();
	}
}
