package werkzeugkasten.gainer.exception;

import java.io.IOException;

public class IORuntimeException extends RuntimeException {

	private static final long serialVersionUID = 668814599015704359L;

	public IORuntimeException(IOException cause) {
		super(cause);
	}
}
