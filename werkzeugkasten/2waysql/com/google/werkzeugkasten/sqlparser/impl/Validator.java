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
		int result = ++current;
		boolean found = false;
		for (int i = result; i < tokens.length; i++) {
			switch (tokens[i]) {
			case BeginSemantic: {
				parameter.addMessage(String.format(UNMATCH, SEMANTICCOMMENT,
						current, pickAround(i, parameter)));
				i = inSemantic(tokens, i, parameter);
				break;
			}
			case EndSemantic: {
				found = true;
				break;
			}
			case EndBrace: {
				if (parameter.endBrace() < 0) {
					unmatch(BRACE, i, parameter);
				}
				break;
			}
			case Identifier: {
				i = verifyIdentifier(tokens, i, parameter);
				break;
			}
			case BeginParenthesis:
			case EndParenthesis: {
				illegalPosition(tokens[i], i, parameter);
				break;
			}

			case Whitespace:
			default: {
				// do nothing ...
				break;
			}
			}
		}
		if (found == false) {
			unmatch(SEMANTICCOMMENT, current, parameter);
		}
		return result;
	}

	protected int verifyIdentifier(TokenKind[] tokens, int current,
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
				illegalPosition(tokens[i], i, parameter);
			}
		}
		return current;
	}

	protected int inParenthesis(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		for (int i = current + 1; i < tokens.length; i++) {
			if (EndParenthesis.equals(tokens[i])) {
				return verifyBrace(tokens, i, parameter);
			} else if (BeginParenthesis.equals(tokens[i])) {
				i = inParenthesis(tokens, i, parameter);
			} else if (Parameter.equals(tokens[i]) == false) {
				illegalPosition(tokens[i], i, parameter);
			}
		}
		unmatch(PARENTHESIS, current, parameter);
		return current;
	}

	protected int verifyBrace(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		for (int i = current + 1; i < tokens.length; i++) {
			if (BeginBrace.equals(tokens[i])) {
				parameter.beginBrace(i);
				return i;
			} else if (EndSemantic.equals(tokens[i])) {
				return i - 1;
			} else if (Whitespace.equals(tokens[i]) == false) {
				illegalPosition(tokens[i], i, parameter);
				parameter.addMessage(String.format(NOTFOUND,
						BeginBrace.label(), current, i));
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
