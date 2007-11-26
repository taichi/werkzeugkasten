package com.google.werkzeugkasten.sqlparser;

public enum TokenKind {

	Text,

	Whitespace,

	// semantic comments
	// Begin : /*
	// End : */
	BeginSemantic, EndSemantic,

	// function identifier
	Identifier,

	// parenthesis ()
	BeginParenthesis, EndParenthesis,

	// brace {}
	BeginBrace, EndBrace
}
