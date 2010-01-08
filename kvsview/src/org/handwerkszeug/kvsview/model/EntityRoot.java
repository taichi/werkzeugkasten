package org.handwerkszeug.kvsview.model;

import java.util.List;

import org.handwerkszeug.kvsview.NotNull;

public interface EntityRoot<T> {

	@NotNull
	List<Node<T>> children();

	/**
	 * エンティティの全件削除的な？
	 */
	void remove();
}
