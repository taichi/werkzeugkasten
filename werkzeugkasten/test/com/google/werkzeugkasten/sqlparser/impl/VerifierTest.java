package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.Status.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;

public class VerifierTest {

	private Verifier verifier;
	private SqlTokenizeContext parameter;

	@Before
	public void setUp() {
		verifier = new Verifier();
		parameter = new SqlTokenizeContextImplForUnitTest();
	}

	@Test
	public void testExecute() {
		assertEquals(Fail, verifier.execute(parameter));
	}

}
