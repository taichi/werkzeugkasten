package werkzeugkasten.gainer.interpreter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultithreadedInterpreter implements Interpreter, Runnable {

	protected static final Logger LOG = LoggerFactory
			.getLogger(MultithreadedInterpreter.class);

	protected Thread processor;
	protected BlockingQueue<String> queue = new LinkedBlockingDeque<String>();
	protected Interpreter inner;

	public MultithreadedInterpreter(Interpreter inner) {
		this.inner = inner;
	}

	@Override
	public void initialize() {
		this.inner.initialize();
		this.processor = new Thread(this);
		this.processor.setName("Gainer#InterpreterThread");
		this.processor.start();
	}

	@Override
	public void dispose() {
		try {
			this.processor.interrupt();
			this.processor.join();
			this.inner.dispose();
		} catch (InterruptedException e) {
			LOG.debug(e.getMessage(), e);
		}
	}

	@Override
	public void process(String datas) {
		this.queue.add(datas);
	}

	@Override
	public void run() {
		try {
			while (true) {
				String datas = this.queue.take();
				this.inner.process(datas);
			}
		} catch (InterruptedException e) {
			LOG.debug(e.getMessage(), e);
		}
	}
}
