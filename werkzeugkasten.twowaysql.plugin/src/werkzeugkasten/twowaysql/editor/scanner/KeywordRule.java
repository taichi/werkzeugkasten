package werkzeugkasten.twowaysql.editor.scanner;

import java.util.Arrays;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordRule;

class KeywordRule implements IRule, IWordDetector {
	static final char[] starts = { 'B', 'I', 'E', 'b', 'i', 'e' };
	static final char[] parts = { 'E', 'G', 'I', 'N', 'F', 'L', 'S', 'D', 'e',
			'g', 'i', 'n', 'f', 'l', 's', 'd' };

	WordRule delegate;

	public KeywordRule(IToken defaultToken, IToken keyword) {
		Arrays.sort(starts);
		Arrays.sort(parts);
		this.delegate = new WordRule(this, defaultToken, true);
		this.delegate.addWord("BEGIN", keyword);
		this.delegate.addWord("IF", keyword);
		this.delegate.addWord("ELSE", keyword);
		this.delegate.addWord("ELSEIF", keyword);
		this.delegate.addWord("END", keyword);

	}

	@Override
	public boolean isWordStart(char c) {
		return -1 < Arrays.binarySearch(starts, c);
	}

	@Override
	public boolean isWordPart(char c) {
		return -1 < Arrays.binarySearch(parts, c);
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		return this.delegate.evaluate(scanner);
	}
}