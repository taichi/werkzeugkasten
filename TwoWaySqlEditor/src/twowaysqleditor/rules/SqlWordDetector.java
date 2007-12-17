package twowaysqleditor.rules;

import org.eclipse.jface.text.rules.IWordDetector;

public class SqlWordDetector implements IWordDetector {

	public boolean isWordStart(char c) {
		switch (c) {
		case 'B':
		case 'I':
		case 'E':
			return true;
		default:
			return false;
		}
	}

	public boolean isWordPart(char c) {
		switch (c) {
		case 'D':
		case 'E':
		case 'F':
		case 'G':
		case 'I':
		case 'L':
		case 'N':
		case 'S':
			return true;
		default:
			return false;
		}
	}

}
