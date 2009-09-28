package org.handwerkszeug.container.event;

// TODO アノテーションからイベントディスパッチ
// TODO メソッド名の規約からイベントディスパッチ
// TODO 設定ファイルからイベントディスパッチ
public interface EventDispatcher {

	void dispatch(Event e);
}
