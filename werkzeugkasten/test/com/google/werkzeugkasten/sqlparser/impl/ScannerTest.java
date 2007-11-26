package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.TokenKind.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenKind;

public class ScannerTest {

	@Test
	public void testExecute() {
		String data = "SELECT * FROM HOGE WHERE MOGE = /*BIND(piro)*/\t10";

		TokenKind[] dataexp = new TokenKind[] {
				// SELECT *
				Text, Text, Text, Text, Text, Text, Whitespace, Text,
				Whitespace,
				// FROM
				Text, Text, Text, Text, Whitespace,
				// HOGE
				Text, Text, Text, Text, Whitespace,
				// WHERE
				Text, Text, Text, Text, Text, Whitespace,
				// MOGE =
				Text, Text, Text, Text, Whitespace, Text, Whitespace,
				// /*
				BeginSemantic, BeginSemantic,
				// BIND(
				Identifier, Identifier, Identifier, Identifier,
				BeginParenthesis,
				// piro)
				Parameter, Parameter, Parameter, Parameter, EndParenthesis,
				// */\t10
				EndSemantic, EndSemantic, Whitespace, Text, Text };

		test(data, Arrays.asList(dataexp));

	}

	protected void test(String sql, List<TokenKind> expected) {
		SqlTokenizeContextImplForUnitTest testdata = new SqlTokenizeContextImplForUnitTest(
				sql);
		Scanner scanner = new Scanner();

		assertEquals(Status.Success, scanner.execute(testdata));
		assertEquals(expected, testdata.getTokens());
	}

	@Test
	public void testExecute2() {
		String data = "SELECT * FROM HOGE WHERE /* IF(0 < piro.length) {*/"
				+ "\r\nMOGE = 10 \r\n /* }*/";
		TokenKind[] dataexp = new TokenKind[] {
				// SELECT *
				Text,
				Text,
				Text,
				Text,
				Text,
				Text,
				Whitespace,
				Text,
				Whitespace,
				// FROM
				Text,
				Text,
				Text,
				Text,
				Whitespace,
				// HOGE
				Text,
				Text,
				Text,
				Text,
				Whitespace,
				// WHERE
				Text,
				Text,
				Text,
				Text,
				Text,
				Whitespace,
				// /*
				BeginSemantic,
				BeginSemantic,
				Whitespace,
				// IF(
				Identifier,
				Identifier,
				BeginParenthesis,
				// 0 < piro.length
				Parameter, Whitespace, Parameter, Whitespace, Parameter,
				Parameter, Parameter, Parameter, Parameter, Parameter,
				Parameter, Parameter,
				Parameter,
				Parameter,
				Parameter,
				// ) {
				EndParenthesis,
				Whitespace,
				BeginBrace,
				// */
				EndSemantic,
				EndSemantic,
				// \r\nMOGE = 10 \r\n
				Whitespace, Whitespace, Text, Text, Text, Text, Whitespace,
				Text, Whitespace, Text, Text, Whitespace, Whitespace,
				Whitespace, Whitespace,
				// /* }*/
				BeginSemantic, BeginSemantic, Whitespace, EndBrace,
				EndSemantic, EndSemantic };
		test(data, Arrays.asList(dataexp));
	}
}
