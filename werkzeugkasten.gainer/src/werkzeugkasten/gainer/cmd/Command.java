package werkzeugkasten.gainer.cmd;

import java.io.OutputStream;

import werkzeugkasten.gainer.conf.Configuration;

public interface Command {

	void emit(Configuration conf, OutputStream out);
}
