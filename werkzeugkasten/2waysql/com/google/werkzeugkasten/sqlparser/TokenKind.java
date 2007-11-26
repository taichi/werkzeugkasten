package com.google.werkzeugkasten.sqlparser;

public enum TokenKind {

	Text,

	// semantic comments
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
