package werkzeugkasten.gainer.cmd;

import java.io.OutputStream;

import werkzeugkasten.gainer.conf.Configuration;
import werkzeugkasten.gainer.util.CommPortUtil;

public class StaticCommand implements Command {

	protected byte[] bytes;

	public StaticCommand(String s) {
		this(s.getBytes());
	}

	public StaticCommand(byte[] b) {
		this.bytes = b;
	}

	protected byte[] getBytes() {
		return this.bytes;
	}

	public void emit(Configuration conf, OutputStream out) {
		CommPortUtil.emit(out, getBytes());
	}
}
