package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.TokenKind.*;

import com.google.werkzeugkasten.meta.Chain;
import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class Scanner implements Chain<Status, SqlTokenizeContext> {

	public Status execute(SqlTokenizeContext parameter) {
		char[] texts = parameter.getFullText();
		scan(parameter, texts);
		return parameter.execute();
	}

	protected void scan(SqlTokenizeContext parameter, char[] texts) {
		for (int i = 0; i < texts.length; i++) {
			char c = texts[i];
			switch (c) {
			case '/': {
				int next = i + 1;
				if (next < texts.length && '*' == texts[next]) {
					i = inSemantic(texts, i, parameter);
					break;
				}
			}
			default: {
				if (setWhitespace(c, i, parameter) == false) {
					parameter.setToken(i, Text);
				}
				break;
			}
			}
		}
	}

	protected boolean setWhitespace(char c, int i, SqlTokenizeContext parameter) {
		switch (c) {
		case ' ':
		case '\r':
		case '\n':
		case '\t': {
			parameter.setToken(i, Whitespace);
			return true;
		}
		}
		return false;
	}

	protected int inSemantic(char[] texts, int current,
			SqlTokenizeContext parameter) {
		int result = current;
		parameter.setToken(result++, BeginSemantic);
		parameter.setToken(result++, BeginSemantic);

		for (int i = result; i < texts.length; i++) {
			char c = texts[i];
			switch (c) {
			case '*': {
				int next = i + 1;
				if (next < texts.length && '/' == texts[next]) {
					parameter.setToken(i, EndSemantic);
					parameter.setToken(next, EndSemantic);
					return next;
				}
				break;
			}
			case '(': {
				i = inParenthesis(texts, i, parameter);
				break;
			}
			case '{': {
				parameter.setToken(i, BeginBrace);
				break;
			}
			case '}': {
				parameter.setToken(i, EndBrace);
				break;
			}
			default: {
				if (setWhitespace(c, i, parameter) == false) {
					parameter.setToken(i, Identifier);
				}
				break;
			}
			}
		}
		return result;
	}

	protected int inParenthesis(char[] texts, int current,
			SqlTokenizeContext parameter) {
		int result = current;
		parameter.setToken(result++, BeginParenthesis);
		for (int i = result; i < texts.length; i++) {
			char c = texts[i];
			if (')' == c) {
				parameter.setToken(i, EndParenthesis);
				return i;
			} else if (setWhitespace(c, i, parameter) == false) {
				parameter.setToken(i, Parameter);
			}
		}
		return result;
	}
}
