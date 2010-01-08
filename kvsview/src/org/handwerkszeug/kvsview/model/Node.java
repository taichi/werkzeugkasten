package org.handwerkszeug.kvsview.model;

import java.util.List;

import org.handwerkszeug.kvsview.NotNull;
import org.handwerkszeug.kvsview.Nullable;

public interface Node<V> {

	/**
	 * 
	 * @return the first child node. other children are accessed by next().
	 */
	@Nullable
	Node<V> firstChild();

	@Nullable
	Node<V> next();

	@Nullable
	Node<V> prev();

	@NotNull
	Node<V> parent();

	boolean hasLeaf();

	@NotNull
	List<Leaf<V>> leaves();

	/**
	 * あるノードに紐付くエンティティをゴソっと消す的な？ <br/>
	 * しかし、このノードはMapReduceで全体の件数に合わせてガンガン変わっていく事を考えると、<br/>
	 * 実データが一体何件消えるのか、予想出来ないので、基本的には必要ない感じ。
	 */
	// void remove();
}
