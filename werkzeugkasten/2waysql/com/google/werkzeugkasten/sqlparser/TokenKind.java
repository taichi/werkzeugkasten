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

	//
	Parameter,

	// brace {}
	BeginBrace, EndBrace
}
