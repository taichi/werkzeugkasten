package com.google.werkzeugkasten.sqlparser.impl;

import org.junit.Test;

import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;

public class TokenizerBuilderTest {

	@Test
	public void testBuild() {
		TokenizerBuilder builder = new TokenizerBuilder("");
		SqlTokenizeContext result = builder.build();
		result.execute();
	}

}
