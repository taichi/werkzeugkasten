package twowaysqleditor.util;

import org.eclipse.jface.text.rules.ICharacterScanner;

public class CharacterScannerUtil {

	public static void rewind(int count, ICharacterScanner scanner) {
		for (int i = 0; i < count; i++) {
			scanner.unread();
		}
	}

	public static int skipWhitespace(ICharacterScanner scanner) {
		int i = 0;
		for (int c = scanner.read(); c != ICharacterScanner.EOF
				&& Character.isWhitespace(c); i++) {
		}
		scanner.unread();
		return i;
	}
}
