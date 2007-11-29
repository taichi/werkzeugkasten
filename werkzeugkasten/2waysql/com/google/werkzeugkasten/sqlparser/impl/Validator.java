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
			switch (tokens[i]) {
			case BeginSemantic: {
				i = inSemantic(tokens, i, parameter);
				break;
			}
			default: {
				// do nothing...
				break;
			}
			}
		}
		if (0 < parameter.getBraces().size()) {
			for (Integer i : parameter.getBraces()) {
				parameter.addMessage(String.format(UNMATCH, BRACE, i,
						pickAround(i, parameter)));
			}
		}
	}

	protected int inSemantic(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		int index = current + 2;
		for (; index < tokens.length; index++) {
			switch (tokens[index]) {
			case BeginSemantic: {
				parameter.addMessage(String.format(UNMATCH, SEMANTICCOMMENT,
						current, pickAround(index, parameter)));
				index = inSemantic(tokens, index, parameter);
				break;
			}
			case EndSemantic: {
				return index + 2;
			}
			case BeginBrace: {
				validateBrace(tokens, index, parameter);
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

		for (int i = current; i < tokens.length; i++) {
			if (BeginParenthesis.equals(tokens[i])) {
				return inParenthesis(tokens, i, parameter);
			} else if (Identifier.equals(tokens[i]) == false) {
				parameter.addMessage(String.format(NOTFOUND, BeginParenthesis
						.label(), current, i, pickAround(i, parameter)));
				return i - 1;
			}
		}
		return current;
	}

	protected int inParenthesis(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		for (int i = current + 1; i < tokens.length; i++) {
			if (EndParenthesis.equals(tokens[i])) {
				return validateBrace(tokens, i + 1, parameter);
			} else if (BeginParenthesis.equals(tokens[i])) {
				i = inParenthesis(tokens, i, parameter);
			} else if (Parameter.equals(tokens[i]) == false) {
				illegalPosition(tokens[i], i, parameter);
			}
		}
		unmatch(PARENTHESIS, current, parameter);
		return current;
	}

	protected int validateBrace(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		for (int i = current; i < tokens.length; i++) {
			if (BeginBrace.equals(tokens[i])) {
				parameter.beginBrace(i);
				return i;
			} else if (EndSemantic.equals(tokens[i])) {
				parameter.addMessage(String.format(NOTFOUND,
						BeginBrace.label(), current, i,
						pickAround(i, parameter)));
				return i - 1;
			} else if (Whitespace.equals(tokens[i]) == false) {
				illegalPosition(tokens[i], i, parameter);
			}
		}
		return current;
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
