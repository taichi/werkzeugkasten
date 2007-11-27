package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.TwoWaySqlMessages.*;

import com.google.werkzeugkasten.meta.Chain;
import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class Verifier implements Chain<Status, SqlTokenizeContext> {

	public Status execute(SqlTokenizeContext parameter) {
		scan(parameter.getTokens(), parameter);
		return parameter.execute();
	}

	protected void scan(TokenKind[] tokens, SqlTokenizeContext parameter) {
		for (int i = 0; i < tokens.length; i++) {
			TokenKind t = tokens[i];
			switch (t) {
			case BeginSemantic: {
				i = inSemantic(tokens, i, parameter);
				break;
			}
			}
		}
	}

	protected int inSemantic(TokenKind[] tokens, int current,
			SqlTokenizeContext parameter) {
		int result = current;
		boolean found = false;
		for (int i = result; i < tokens.length; i++) {

		}
		if (found == false) {
			parameter.addMessage(String.format(UNMATCH, SEMANTICCOMMENT,
					current));
		}
		return result;
	}

	protected String pickAround(int offset, SqlTokenizeContext parameter) {
		StringBuilder stb = new StringBuilder();
		char[] fullText = parameter.getFullText();
		if (fullText.length < 6) {
			stb.append(fullText);
		} else if (offset < 3) {
			stb.append(fullText, 0, 5);
		} else {
			int start = offset - 3;
			if (fullText.length < start + 6) {
				// FIXME
			} else {
				stb.append(fullText, start, 6);
			}
		}
		return stb.toString();
	}
}
