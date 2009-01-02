package werkzeugkasten.twowaysql.editor.scanner;

import org.eclipse.jface.text.rules.ICharacterScanner;

public class ResettableScanner implements ICharacterScanner {

	protected ICharacterScanner delegate;

	protected int read;

	public ResettableScanner(ICharacterScanner delegate) {
		this.delegate = delegate;
	}

	public int getColumn() {
		return delegate.getColumn();
	}

	public char[][] getLegalLineDelimiters() {
		return delegate.getLegalLineDelimiters();
	}

	public int read() {
		int ch = delegate.read();
		if (ch != ICharacterScanner.EOF) {
			read++;
		}
		return ch;
	}

	public void unread() {
		read--;
		delegate.unread();
	}

	public void mark() {
		read = 0;
	}

	public void reset() {
		while (0 < read) {
			unread();
		}
		while (read < 0) {
			read();
		}
	}
}
