package com.google.werkzeugkasten.sqlparser;

import static com.google.werkzeugkasten.sqlparser.TwoWaySqlMessages.*;

public enum TokenKind {

	Text(TEXT),

	Whitespace(WHITESPACE),

	// semantic comments
	// Begin : /*
	// End : */
	BeginSemantic(SEMANTICCOMMENT), EndSemantic(SEMANTICCOMMENT),

	// function identifier
	Identifier(IDENTIFIER),

	// parenthesis ()
	BeginParenthesis(BEGINPARENTHESIS), EndParenthesis(PARENTHESIS),

	// in parenthesis texts
	Parameter(PARAMETER),

	// brace {}
	BeginBrace(BEGINBRACE), EndBrace(BRACE);

	private String label;

	private TokenKind(String s) {
		this.label = s;
	}

	public String label() {
		return this.label;
	}
}
