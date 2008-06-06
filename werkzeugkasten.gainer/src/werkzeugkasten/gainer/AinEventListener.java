package werkzeugkasten.gainer;

import java.util.EventListener;
import java.util.List;

public interface AinEventListener extends EventListener {

	/**
	 * AIN からの入力イベントを通知。<br/>List#sizeは、Configurationに依存してサイズが決定される。
	 * 4,8,16のいずれかとなる。
	 * 
	 * @param datas
	 */
	void updated(List<Number> datas);
}
