package com.google.werkzeugkasten.sqlparser.impl;

import org.junit.Test;

import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;

public class TokenizerBuilderTest {

	String data = "SELECT * FROM HOGE WHERE MOGE = /*BIND(piro)*/10";
	String data2 = "SELECT * FROM HOGE WHERE /* IF(0 < piro.length) {*/\r\n"
			+ "MOGE = 10 \r\n /* }*/";
	String err = "SELECT * FROM HOGE WHERE MOGE = /*BIND(piro)10";
	String err2 = "SELECT * FROM HOGE WHERE MOGE = /*BIND(piro*/10";
	String error3 = "SELECT * FROM HOGE WHERE /* IF(0 < piro.length) {*/\r\n"
			+ "MOGE = 10 \r\n /* }*/";

	@Test
	public void testBuild() {
		TokenizerBuilder builder = new TokenizerBuilder(data);
		SqlTokenizeContext result = builder.build();
		result.execute();
	}

}
