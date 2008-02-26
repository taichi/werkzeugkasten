package werkzeugkasten.common.exception;

import java.io.FileNotFoundException;

public class FileNotFoundRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -4218925841270908108L;

	public FileNotFoundRuntimeException(FileNotFoundException e) {
		super(e);
	}
}
