package org.handwerkszeug.kvsview.model.pb;

public interface KeyGenerator {

	String nextKey();

	String nextKey(ModelPB.Node node);
}
