package werkzeugkasten.gainer;

import java.util.EventListener;

public interface OnBoardButtonEventListener extends EventListener {

	/**
	 * ボード上に実装されたボタンが押下された事を通知する。<br/>
	 * 
	 * @param pressed
	 *            true 押下状態 / false 非押下状態
	 */
	void updated(boolean pressed);
}
