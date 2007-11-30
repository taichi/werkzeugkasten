package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.TwoWaySqlMessages.*;
import static com.google.werkzeugkasten.sqlparser.TokenKind.*;

import com.google.werkzeugkasten.meta.Chain;
import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class Validator implements Chain<Status, SqlTokenizeContext> {

	public Status execute(SqlTokenizeContext parameter) {
		scan(parameter.getTokens(), parameter);
		return parameter.getMessages().size() < 1 ? parameter.execute()
				: Status.Fail;
	}

	protected void scan(TokenKind[] tokens, SqlTokenizeContext parameter) {
		for (int i = 0; i < tokens.length; i++) {
			if (BeginSemantic.equals(tokens[i])) {
				i = inSemantic(tokens, i, parameter);
			}
		}
		if (0 < parameter.getBraces().size()) {
			for (Integer i : parameter.getBraces()) {
				parameter.addMessage(String.format(UNMATCH, BEGINBRACE, i,
						pickAround(i, parameter)));
			}
		}
	}

	protected int inSemantic(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		int index = current + 3;
		for (; index < tokens.length; index++) {
			switch (tokens[index]) {
			case BeginSemantic: {
				parameter.addMessage(String.format(UNMATCH, SEMANTICCOMMENT,
						current, pickAround(index, parameter)));
				return index - 1;
			}
			case EndSemantic: {
				return index + 2;
			}
			case BeginBrace: {
				index = validateBrace(tokens, index, parameter);
				break;
			}
			case EndBrace: {
				if (parameter.endBrace() < 0) {
					unmatch(BRACE, index, parameter);
				}
				break;
			}
			case Identifier: {
				index = validateIdentifier(tokens, index, parameter);
				break;
			}
			case BeginParenthesis: {
				parameter.addMessage(String.format(NOTFOUND,
						Identifier.label(), current, index, pickAround(index,
								parameter)));
				break;
			}

			case Whitespace:
			default: {
				// do nothing ...
				break;
			}
			}
		}
		unmatch(SEMANTICCOMMENT, current, parameter);
		return index;
	}

	protected int validateIdentifier(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		for (int i = current - 1; -1 < i; i--) {
			if (BeginSemantic.equals(tokens[i])) {
				break;
			} else if (Whitespace.equals(tokens[i]) == false) {
				illegalPosition(tokens[i], i, parameter);
			}
		}
		int result = current;
		for (; result < tokens.length; result++) {
			if (BeginParenthesis.equals(tokens[result])) {
				return inParenthesis(tokens, result, false, parameter);
			} else if (Identifier.equals(tokens[result]) == false) {
				parameter.addMessage(String.format(NOTFOUND, BeginParenthesis
						.label(), current, result,
						pickAround(result, parameter)));
				return result - 1;
			}
		}
		return result;
	}

	protected int inParenthesis(TokenKind[] tokens, int current,
			boolean inline, SqlTokenizeContext parameter) {
		int result = current + 1;
		loop: for (; result < tokens.length; result++) {
			switch (tokens[result]) {
			case BeginParenthesis: {
				result = inParenthesis(tokens, result, true, parameter);
				break;
			}
			case EndParenthesis: {
				if (inline == false) {
					result = lookBrace(tokens, result, parameter);
				}
				return result;
			}
			case BeginBrace:
			case EndSemantic:
				result--;
				break loop;
			case Parameter:
				break;
			default: {
				illegalPosition(tokens[result], result, parameter);
			}
			}
		}
		unmatch(PARENTHESIS, current, parameter);
		return result;
	}

	protected int lookBrace(TokenKind[] tokens, int result,
			SqlTokenizeContext parameter) {
		boolean found = false;
		int index = result + 1;
		loop: for (; index < tokens.length; index++) {
			switch (tokens[index]) {
			case BeginBrace: {
				found = true;
				index = validateBrace(tokens, index, parameter);
			}
			case EndSemantic: {
				break loop;
			}
			case Whitespace: {
				break;
			}
			default: {
				illegalPosition(tokens[index], index, parameter);
			}
			}
		}
		if (found == false) {
			parameter.addMessage(String.format(NOTFOUND, BeginBrace.label(),
					result, index, pickAround(result, parameter)));
		}
		return index;
	}

	protected int validateBrace(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		// TODO backfire may be need?
		// for (int i = current - 1; -1 < i; i--) {
		// if (EndParenthesis.equals(tokens[i])) {
		// break;
		// } else if (Whitespace.equals(tokens[i]) == false) {
		// illegalPosition(tokens[i], i, parameter);
		// break;
		// }
		// }
		parameter.beginBrace(current);
		int result = current + 1;
		for (; result < tokens.length; result++) {
			switch (tokens[result]) {
			case Whitespace:
				break;
			case EndSemantic: {
				return result - 1;
			}
			case EndBrace: {
				parameter.endBrace();
			}
			default: {
				illegalPosition(tokens[result], result, parameter);
				break;
			}
			}
		}
		return result;
	}

	protected void unmatch(String name, int beginOffset,
			SqlTokenizeContext parameter) {
		parameter.addMessage(String.format(UNMATCH, name, beginOffset,
				pickAround(beginOffset, parameter)));
	}

	protected void illegalPosition(TokenKind t, int current,
			SqlTokenizeContext parameter) {
		parameter.addMessage(String.format(ILLEGALPOSITION, t.label(), current,
				pickAround(current, parameter)));
	}

	protected String pickAround(int offset, SqlTokenizeContext parameter) {
		return pickAround(offset, parameter.getFullText());
	}

	protected static final int PICK_LENGTH = 7;

	protected String pickAround(int offset, char[] fullText) {
		if (offset < 0 || fullText == null || fullText.length <= offset) {
			return "";
		}
		StringBuilder stb = new StringBuilder();
		if (fullText.length < PICK_LENGTH) {
			stb.append(fullText);
		} else if (offset < 3) {
			stb.append(fullText, 0, PICK_LENGTH);
		} else {
			int begin = offset - 3;
			if (fullText.length < begin + PICK_LENGTH) {
				stb
						.append(fullText, fullText.length - PICK_LENGTH,
								PICK_LENGTH);
			} else {
				stb.append(fullText, begin, PICK_LENGTH);
			}
		}
		return stb.toString();
	}
}
