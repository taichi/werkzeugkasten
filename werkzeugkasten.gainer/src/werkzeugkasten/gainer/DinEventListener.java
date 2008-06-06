package werkzeugkasten.gainer;

import java.util.EventListener;
import java.util.List;

public interface DinEventListener extends EventListener {

	void updated(List<Boolean> datas);
}
