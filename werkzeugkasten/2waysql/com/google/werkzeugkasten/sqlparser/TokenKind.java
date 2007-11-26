package com.google.werkzeugkasten.sqlparser;

public enum TokenKind {

	Text,

	// semantic comments
	// Begin : /*
	// End : */
	BeginSemantic, EndSemantic,

	// function identifier
	Identifier,

	// parenthesis ()
	BeginParenthesis, EndParenthesis,

	// 
	Parameters,

	// brace {}
	BeginBrace, EndBrace
}
