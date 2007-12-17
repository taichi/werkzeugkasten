package twowaysqleditor.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;

import twowaysqleditor.util.CharacterScannerUtil;

public class BindRule extends SingleLineRule {

	public BindRule(IToken token) {
		super("/*", "*/", token);
	}

	@Override
	protected boolean sequenceDetected(ICharacterScanner scanner,
			char[] sequence, boolean eofAllowed) {
		char[][] delims = scanner.getLegalLineDelimiters();
		for (int i = 1, c = scanner.read(); c != ICharacterScanner.EOF; i++, c = scanner
				.read()) {
			for (int j = 0; j < delims.length; j++) {
				for (int k = 0; k < delims[j].length; k++) {
					if (c == delims[j][k]) {
						CharacterScannerUtil.rewind(i, scanner);
						return false;
					}
				}
			}
			if (c == '*') {
				if (i < 2) {
					CharacterScannerUtil.rewind(i, scanner);
					return false;
				} else {
					i++;
					c = scanner.read();
					scanner.unread();
					if (c == '/') {
						scanner.unread();
						return true;
					}
				}
			} else if (Character.isWhitespace(c) == false && c != '.'
					&& (c != '$' && i == 1)
					&& Character.isJavaIdentifierPart(c) == false) {
				CharacterScannerUtil.rewind(i, scanner);
				return false;
			}
		}
		return true;
	}
}
