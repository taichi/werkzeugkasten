package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.TokenKind.*;
import static com.google.werkzeugkasten.sqlparser.Status.*;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.TokenKind;
import com.google.werkzeugkasten.sqlparser.TokenLeaf;
import com.google.werkzeugkasten.sqlparser.TokenNode;

public class TokenizerTest {

	Tokenizer tokenizer;

	@Before
	public void setUp() throws Exception {
		tokenizer = new Tokenizer();
	}

	@Test
	public void testExecute() {
		SqlTokenizeContext context = new TokenizerBuilder(
				"  zzz/*? aaa() {*/ bbb  /*?} ccc(43<hoge.size()) {*/ddd/*? }*/")
				.build();
		assertEquals(Success, context.execute());
		TokenNode root = context.getRoot();
		List<TokenLeaf> kids = root.getChildren();
		assertEquals(3, kids.size());
		assertTrue(kids.get(0) instanceof Literal);

		Functor aaa = (Functor) kids.get(1);
		assertEquals("aaa", aaa.getName());
		assertEquals("", aaa.getExpression());
		Literal bbb = (Literal) aaa.getChildren().get(0);
		assertEquals(18, bbb.getOffset());
		assertEquals(6, bbb.getLength());

		Functor ccc = (Functor) kids.get(2);
		assertEquals("ccc", ccc.getName());
		assertEquals("43<hoge.size()", ccc.getExpression());
		System.out.println(root);
	}

	@Test
	public void testRead() {
		TokenKind[] tokens = new TokenKind[] { Whitespace, Whitespace,
				Whitespace, Whitespace, Identifier, Identifier };
		assertEquals(4, tokenizer.read(tokens, 0, Whitespace));
		assertEquals(6, tokenizer.read(tokens, 4, Identifier));
	}

	@Test
	public void testBack() {
		TokenKind[] tokens = new TokenKind[] { Whitespace, Whitespace,
				Whitespace, Whitespace, Identifier, Identifier };
		assertEquals(3, tokenizer.back(tokens, 5, Identifier));
	}
}
