package twowaysqleditor.rules;

import java.util.Arrays;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.SingleLineRule;

import twowaysqleditor.util.CharacterScannerUtil;

public class BeginRule extends SingleLineRule {

	public static final int[] BEGIN = { 'B', 'E', 'G', 'I', 'N' };

	public BeginRule(IToken token) {
		super("/*", "*/", token);
	}

	@Override
	protected boolean sequenceDetected(ICharacterScanner scanner,
			char[] sequence, boolean eofAllowed) {
		int[] ary = new int[4];
		int count = 4;
		for (int i = 0; i < ary.length; i++) {
			ary[i] = scanner.read();
		}
		if (Arrays.equals(BEGIN, ary)) {
			count += CharacterScannerUtil.skipWhitespace(scanner);
			int c = scanner.read();
			if (c == '*') {
				c = scanner.read();
				count += 1;
				if (c == '/') {
					scanner.unread();
					scanner.unread();
					return true;
				}
			}
		}
		CharacterScannerUtil.rewind(count, scanner);
		return false;
	}
}
