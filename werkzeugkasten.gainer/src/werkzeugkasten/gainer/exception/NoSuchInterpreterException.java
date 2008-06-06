package werkzeugkasten.gainer.exception;

public class NoSuchInterpreterException extends RuntimeException {

	private static final long serialVersionUID = 1312893598747526168L;

	protected String command;

	public NoSuchInterpreterException(String data) {
		this.command = data;
	}

	public String getCommand() {
		return this.command;
	}

}
