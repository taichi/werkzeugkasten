package werkzeugkasten.twowaysql.editor.scanner;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.WordRule;

import werkzeugkasten.common.util.StringUtil;

public class KeywordRule implements IRule, IWordDetector {

	Set<Character> startSet = new HashSet<Character>();
	Set<Character> partSet = new HashSet<Character>();

	WordRule delegate;

	public KeywordRule(IToken defaultToken, IToken keyword) {
		this.delegate = new WordRule(this, defaultToken, true);
		addWord("BEGIN", keyword);
		addWord("IF", keyword);
		addWord("ELSE", keyword);
		addWord("ELSEIF", keyword);
		addWord("END", keyword);
		addWord("?", keyword);
	}

	public void addWord(String string, IToken token) {
		if (StringUtil.isEmpty(string) == false) {
			this.delegate.addWord(string, token);
			char[] chars = string.toCharArray();
			startSet.add(chars[0]);
			if (1 < chars.length) {
				for (int i = 1; i < chars.length; i++) {
					partSet.add(chars[i]);
				}
			}
		}
	}

	@Override
	public boolean isWordStart(char c) {
		return this.startSet.contains(c);
	}

	@Override
	public boolean isWordPart(char c) {
		return this.partSet.contains(c);
	}

	@Override
	public IToken evaluate(ICharacterScanner scanner) {
		return this.delegate.evaluate(scanner);
	}
}