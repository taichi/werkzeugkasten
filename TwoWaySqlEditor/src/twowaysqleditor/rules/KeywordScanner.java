package twowaysqleditor.rules;

import static twowaysqleditor.Constants.*;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

import twowaysqleditor.ColorManager;

public class KeywordScanner extends RuleBasedScanner {

	public KeywordScanner(ColorManager manager) {
		IToken string = new Token(new TextAttribute(manager.getColor(KEYWORD),
				null, SWT.BOLD));

		WordRule words = new WordRule(new SqlWordDetector());
		words.addWord("BEGIN", string);
		words.addWord("IF", string);
		words.addWord("ELSE", string);
		words.addWord("END", string);

		IRule[] rules = { words, new WhitespaceRule(new WhitespaceDetector()) };

		setRules(rules);
	}
}
