package werkzeugkasten.gainer.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import werkzeugkasten.gainer.conf.Configuration;
import werkzeugkasten.gainer.util.StringUtil;

public class CompositeInterpreter implements Interpreter {

	protected Map<Character, Interpreter> interpreters = new HashMap<Character, Interpreter>();

	protected Configuration config;

	public CompositeInterpreter(Configuration config) {
		this.config = config;
	}

	@Override
	public void initialize() {
		Interpreter ain = new DefaultInterpreter() {
			@Override
			public void process(String datas) {
				processAIN(datas);
			}
		};
		this.interpreters.put('i', ain);
		this.interpreters.put('I', ain);

		Interpreter din = new DefaultInterpreter() {
			@Override
			public void process(String datas) {
				processDIN(datas);
			}
		};
		this.interpreters.put('r', din);
		this.interpreters.put('R', din);

		Interpreter on = new DefaultInterpreter() {
			@Override
			public void process(String datas) {
				processOnBoardButtonOn(datas);
			}
		};
		this.interpreters.put('N', on);

		Interpreter off = new DefaultInterpreter() {
			@Override
			public void process(String datas) {
				processOnBoardButtonOff(datas);
			}
		};
		this.interpreters.put('F', off);

		for (Interpreter i : interpreters.values()) {
			i.initialize();
		}
	}

	protected void processAIN(String datas) {
		int ainSize = this.config.type().ain();
		List<Number> ain = new ArrayList<Number>(ainSize);
		for (int i = 0; i < ainSize; i++) {
			String s = datas.substring(2 * i + 1, 2 * (i + 1) + 1);
			ain.add(Integer.parseInt(s, 16));
		}
		this.config.notifyAin(ain);
	}

	protected void processDIN(String datas) {
		int dinSize = this.config.type().din();
		List<Boolean> din = new ArrayList<Boolean>(dinSize);
		int value = Integer.parseInt(datas.substring(1, 5), 16);
		for (int i = 0; i < dinSize; i++) {
			int c = 1 & (value >> i);
			din.add(c == 1);
		}
		this.config.notifyDin(din);
	}

	protected void processOnBoardButtonOn(String datas) {
		this.config.notifyButton(true);
	}

	protected void processOnBoardButtonOff(String datas) {
		this.config.notifyButton(false);
	}

	@Override
	public void dispose() {
		for (Interpreter i : interpreters.values()) {
			i.dispose();
		}
	}

	@Override
	public void process(String datas) {
		if (StringUtil.isEmpty(datas) == false) {
			Character c = datas.charAt(0);
			Interpreter i = this.interpreters.get(c);
			if (i != null) {
				i.process(datas);
			}
		}
	}

}
