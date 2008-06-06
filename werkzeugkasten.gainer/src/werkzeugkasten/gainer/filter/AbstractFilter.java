package werkzeugkasten.gainer.filter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import werkzeugkasten.gainer.AinEventListener;

public abstract class AbstractFilter implements Filter {

	protected String name;
	protected AinEventListener listener;
	protected LinkedList<List<Number>> history = new LinkedList<List<Number>>();
	protected int historySize;

	public AbstractFilter(String name, AinEventListener listener) {
		this(name, listener, 8);
	}

	public AbstractFilter(String name, AinEventListener listener,
			int historySize) {
		this.name = name;
		this.listener = listener;
		this.historySize = historySize;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public void updated(List<Number> inputs) {
		this.history.addLast(inputs);
		if (this.historySize < this.history.size()) {
			this.history.removeFirst();
		}

		List<Number> newone = new ArrayList<Number>(inputs.size());
		int inputSize = inputs.size();
		for (int i = 0; i < inputSize; i++) {
			List<Number> buffer = new ArrayList<Number>(historySize);
			for (int j = 0; j < historySize; j++) {
				List<Number> old = this.history.get(j);
				if (old.size() <= inputs.size()) {
					buffer.add(old.get(i));
				}
			}
			newone.add(doFilter(buffer));
		}
		this.listener.updated(newone);
	}

	protected abstract float doFilter(List<Number> history);
}
