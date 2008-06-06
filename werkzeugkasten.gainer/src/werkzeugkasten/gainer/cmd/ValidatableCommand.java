package werkzeugkasten.gainer.cmd;

import java.io.OutputStream;

import werkzeugkasten.gainer.conf.Configuration;

public abstract class ValidatableCommand implements Command {

	protected Command cmd;

	protected ValidatableCommand(Command cause) {
		this.cmd = cause;
	}

	protected abstract void validate(Configuration conf);

	@Override
	public void emit(Configuration conf, OutputStream out) {
		validate(conf);
		this.cmd.emit(conf, out);
	}
}
