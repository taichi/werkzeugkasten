package werkzeugkasten.twowaysql.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

import werkzeugkasten.twowaysql.Constants;

public class TwoWaySqlPartitionScanner extends RuleBasedPartitionScanner {
	// TODO will be tune. cf. FastJavaPartitionScanner

	// ?? needly ??
	static class EmptyCommentRule extends WordRule implements IPredicateRule {
		protected IToken success;

		public EmptyCommentRule(final String word, IToken success) {
			super(new IWordDetector() {
				char[] chars = word.toCharArray();
				{
					Arrays.sort(chars);
				}

				@Override
				public boolean isWordStart(char c) {
					return chars[0] == c;
				}

				@Override
				public boolean isWordPart(char c) {
					return -1 < Arrays.binarySearch(chars, c);
				}

			});
			this.success = success;
			addWord(word, success);
		}

		@Override
		public IToken evaluate(ICharacterScanner scanner, boolean resume) {
			return evaluate(scanner);
		}

		@Override
		public IToken getSuccessToken() {
			return success;
		}
	}

	public TwoWaySqlPartitionScanner() {
		super();
		IToken txt = new Token(Constants.CT_TEXT);
		IToken blockComment = new Token(Constants.CT_BLOCKCOMMENT);
		IToken lineComment = new Token(Constants.CT_LINECOMMENT);

		setDefaultReturnToken(txt);

		List<IPredicateRule> list = new ArrayList<IPredicateRule>();

		list.add(new EndOfLineRule("--", lineComment));
		list.add(new EndOfLineRule("#", lineComment));
		list.add(new MultiLineRule("/*", "*/", blockComment));
		list.add(new EmptyCommentRule("/**/", blockComment));

		setPredicateRules(list.toArray(new IPredicateRule[list.size()]));
	}
}
