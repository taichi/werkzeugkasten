package werkzeugkasten.gainer;

import java.util.EventListener;
import java.util.List;

public interface AinEventListener extends EventListener {

	void updated(List<Number> datas);
}
