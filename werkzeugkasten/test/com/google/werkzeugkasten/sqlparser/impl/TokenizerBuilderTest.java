package com.google.werkzeugkasten.sqlparser.impl;

import static org.junit.Assert.*;
import static com.google.werkzeugkasten.sqlparser.Status.*;

import org.junit.Test;

import com.google.werkzeugkasten.sqlparser.SqlConstructionContext;
import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.TokenNode;

public class TokenizerBuilderTest {

	String data = "SELECT * FROM HOGE WHERE MOGE = /*BIND(piro) {*/10/*}*/";
	String data2 = "SELECT * FROM HOGE WHERE /* IF(0 < piro.length) {*/\r\n"
			+ "MOGE = 10 \r\n /* }*/";

	@Test
	public void testBuild() {
		TokenizerBuilder builder = new TokenizerBuilder(data);
		SqlTokenizeContext result = builder.build();
		assertEquals(Success, result.execute());

		SqlConstructionContext parameter = new SqlConstructionContextImpl(
				result.getFullText(), result.getTokens());
		TokenNode root = result.getRoot();
		assertEquals(Success, root.execute(parameter));
		assertEquals("SELECT * FROM HOGE WHERE MOGE = ?", parameter.getBuffer()
				.toString());
	}

}
