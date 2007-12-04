package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.TokenKind.*;

import java.util.regex.Pattern;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class Literal extends AbstractToken {

	protected static Pattern skippingTxt = Pattern.compile("\\s*(AND|OR)",
			Pattern.CASE_INSENSITIVE);

	protected static Pattern skippableTxt = Pattern.compile(
			".*(WHERE|AND|OR)\\s*$", Pattern.CASE_INSENSITIVE);

	public Literal(int offset) {
		super(offset);
	}

	public Status execute(SqlConstructionContext parameter) {
		int offset = getOffset();
		int current = parameter.getCursor();
		current = offset < current ? current : offset;
		if (isSkippable(parameter)) {
			TokenKind[] tokens = parameter.getTokens();
			int from = skip(tokens, current, Whitespace);
			int to = skip(tokens, from, Text);
			String s = String.copyValueOf(parameter.getFullText(), current, to
					- current);
			if (skippingTxt.matcher(s).matches()) {
				current = to + 1;
			}
		}
		int length = getLength() - (current - getOffset());
		parameter.getBuffer().append(parameter.getFullText(), current, length);
		parameter.setCursor(current + length);
		parameter.addStatus(Status.Success);
		return parameter.execute();
	}

	protected boolean isSkippable(SqlConstructionContext context) {
		return skippableTxt.matcher(context.getBuffer()).matches();
	}

	protected int skip(TokenKind[] tokens, int index, TokenKind t) {
		for (; tokens[index].equals(t) && index < tokens.length; index++)
			;
		return index;
	}
}
