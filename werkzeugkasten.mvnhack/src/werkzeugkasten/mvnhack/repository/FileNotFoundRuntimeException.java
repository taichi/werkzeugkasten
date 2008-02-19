package werkzeugkasten.mvnhack.repository;

import java.io.FileNotFoundException;

public class FileNotFoundRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 7377662740592882819L;

	public FileNotFoundRuntimeException(FileNotFoundException cause) {
		super(cause);
	}

}
