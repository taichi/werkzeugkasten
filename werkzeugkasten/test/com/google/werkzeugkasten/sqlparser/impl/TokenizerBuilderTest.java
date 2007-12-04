package com.google.werkzeugkasten.sqlparser.impl;

import static org.junit.Assert.*;
import static com.google.werkzeugkasten.sqlparser.Status.*;

import org.junit.Test;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.TokenNode;

public class TokenizerBuilderTest {

	String data = "SELECT * FROM HOGE WHERE MOGE = /*? BIND(piro) {*/10/*?}*/";
	String data2 = "SELECT * FROM HOGE WHERE /*? IF(0 < piro) {*/\r\n"
			+ "MOGE = 10 \r\n /*? } IF(0 < fuga){*/\t AND MOGE = 22/*?}*/";

	@Test
	public void testBuild() {
		assertEquals("SELECT * FROM HOGE WHERE MOGE = ?", construct(data));
		assertEquals("SELECT * FROM HOGE WHERE  MOGE = 22", construct(data2));
	}

	protected String construct(String data) {
		TokenizerBuilder builder = new TokenizerBuilder(data);
		SqlTokenizeContext result = builder.build();
		try {
			assertEquals(Success, result.execute());
		} catch (AssertionError e) {
			System.out.println(result.getMessages());
			throw e;
		}

		SqlConstructionContext parameter = new SqlConstructionContextImpl(
				result.getFullText(), result.getTokens());
		parameter.set("piro", -5);
		parameter.set("fuga", 10);
		TokenNode root = result.getRoot();
		assertEquals(Success, root.execute(parameter));
		return parameter.getBuffer().toString();
	}

}
