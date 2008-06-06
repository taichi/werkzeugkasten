package werkzeugkasten.gainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.gainer.cmd.Command;
import werkzeugkasten.gainer.cmd.SerialPortHandler;
import werkzeugkasten.gainer.conf.Configuration;
import werkzeugkasten.gainer.interpreter.CompositeInterpreter;
import werkzeugkasten.gainer.interpreter.MultithreadedInterpreter;
import werkzeugkasten.gainer.lifecycle.Disposable;
import werkzeugkasten.gainer.lifecycle.Initializable.Initialize;

public class GainerDriver implements Disposable {

	protected static final Logger LOG = LoggerFactory
			.getLogger(GainerDriver.class);

	protected SerialPortHandler handler;

	public GainerDriver() {
	}

	@Initialize
	public void initialize(Configuration config) {
		try {
			this.handler = new SerialPortHandler(config,
					new MultithreadedInterpreter(new CompositeInterpreter(
							config)));
			this.handler.initialize();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void dispose() {
		this.handler.dispose();
	}

	public void execute(Command cmd) {
		this.handler.execute(cmd);
	}
}
