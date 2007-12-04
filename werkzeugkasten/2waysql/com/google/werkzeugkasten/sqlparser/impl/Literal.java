package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.TokenKind.*;

import java.util.List;
import java.util.regex.Pattern;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class Literal extends AbstractToken {

	protected static Pattern skippableTxt = Pattern.compile(
			"[ \r\n\t]*(AND|OR)", Pattern.CASE_INSENSITIVE);

	public Literal(int offset) {
		super(offset);
	}

	public Status execute(SqlConstructionContext parameter) {
		int current = parameter.getCursor();
		if (isSkippable(parameter)) {
			TokenKind[] tokens = parameter.getTokens();
			int from = skip(tokens, current, Whitespace);
			int to = skip(tokens, from, Text);
			String s = String.copyValueOf(parameter.getFullText(), current, to
					- from);
			if (skippableTxt.matcher(s).matches()) {
				current = to + 1;
			}
		}
		int length = getLength() - current - getOffset();
		parameter.getBuffer().append(parameter.getFullText(), current, length);
		parameter.setCursor(current + length);
		return Status.Success;
	}

	protected boolean isSkippable(SqlConstructionContext context) {
		List<Status> list = context.getStatusCopy();
		return list.isEmpty() == false
				&& list.contains(Status.Success) == false;
	}

	protected int skip(TokenKind[] tokens, int index, TokenKind t) {
		for (; tokens[index].equals(t) && index < tokens.length; index++)
			;
		return index - 1;
	}
}
