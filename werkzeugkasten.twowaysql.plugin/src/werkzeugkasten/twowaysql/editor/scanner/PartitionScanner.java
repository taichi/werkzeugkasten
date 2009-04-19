package werkzeugkasten.twowaysql.editor.scanner;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants;

public class PartitionScanner implements IPartitionTokenScanner {

	protected IDocument document;

	// temporary values.
	protected int tokenOffset;
	protected int tokenLength;

	protected static final int EOF = ICharacterScanner.EOF;
	protected static final IToken PT_TXT = new Token(
			Constants.CONTENT_TYPE_TEXT);
	protected static final IToken PT_BLOCKCOMMENT = new Token(
			Constants.CONTENT_TYPE_BLOCKCOMMENT);
	protected static final IToken PT_LINECOMMENT = new Token(
			Constants.CONTENT_TYPE_LINECOMMENT);

	@Override
	public IToken nextToken() {
		int offset = this.tokenOffset + this.tokenLength;
		int prefix = offset;
		int end = 0;
		IToken result = Token.EOF;
		switch (read(offset)) {
		case EOF: {
			return Token.EOF;
		}
		case '/': {
			if (read(offset + 1) == '*') {
				prefix = offset + 2;
				result = PT_BLOCKCOMMENT;
				break;
			}
		}
		case '-': {
			if (read(offset + 1) == '-') {
				prefix = offset + 2;
				result = PT_LINECOMMENT;
				break;
			}
		}
		case '#': {
			prefix = offset + 1;
			result = PT_LINECOMMENT;
			break;
		}
		default: {
			prefix = offset + 1;
			result = PT_TXT;
			break;
		}
		}

		loop: while (true) {
			switch (read(prefix++)) {
			case EOF: {
				end = prefix - 1;
				break loop;
			}
			case '*': {
				if (result == PT_BLOCKCOMMENT) {
					if (read(prefix) == '/') {
						end = prefix + 1;
						break loop;
					}
				}
				break;
			}
			case '\n': {
				if (result == PT_LINECOMMENT) {
					end = prefix;
					break loop;
				}
				break;
			}
			case '/': {
				if (read(prefix) == '*') {
					end = prefix - 1;
					break loop;
				}
				break;
			}
			case '-': {
				if (read(prefix) == '-') {
					end = prefix - 1;
					break loop;
				}
				break;
			}
			case '#': {
				end = prefix - 1;
				break loop;
			}
			case '\"': {
				prefix = skipToNextQuote(prefix + 1, '\"');
				break;
			}
			case '\'': {
				prefix = skipToNextQuote(prefix + 1, '\'');
				break;
			}
			}
		}
		emit(offset, end);
		return result;
	}

	protected int read(int offset) {
		try {
			if (offset < this.document.getLength()) {
				return this.document.getChar(offset);
			}
		} catch (BadLocationException e) {
			Activator.log(e);
		}
		return EOF;
	}

	protected void emit(int begin, int end) {
		this.tokenOffset = begin;
		this.tokenLength = end - begin;
	}

	protected int skipToNextQuote(int offset, int quote) {
		for (int i = read(offset); i != quote; i = read(++offset)) {
			if (i == EOF) {
				return offset;
			}
		}
		return offset + 1;
	}

	@Override
	public void setRange(IDocument document, int offset, int length) {
		setPartialRange(document, offset, length, Constants.CONTENT_TYPE_TEXT,
				0);
	}

	@Override
	public void setPartialRange(IDocument document, int offset, int length,
			String contentType, int partitionOffset) {
		try {
			String string = document.get(offset, length);
			System.out.printf("$PT$[%d:%d:%s:%d] [%s] %n", offset, length,
					contentType, partitionOffset, string);
		} catch (BadLocationException e) {
		}
		this.document = document;
		this.tokenOffset = offset;
		this.tokenLength = 0;
	}

	@Override
	public int getTokenOffset() {
		return this.tokenOffset;
	}

	@Override
	public int getTokenLength() {
		return this.tokenLength;
	}

}
