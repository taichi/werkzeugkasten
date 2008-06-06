package werkzeugkasten.gainer.interpreter;

import werkzeugkasten.gainer.lifecycle.LifeCycle;

public interface Interpreter extends LifeCycle {

	void process(String datas);
}
