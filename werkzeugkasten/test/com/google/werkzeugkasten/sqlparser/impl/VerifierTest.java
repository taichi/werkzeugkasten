package com.google.werkzeugkasten.sqlparser.impl;

import static com.google.werkzeugkasten.sqlparser.Status.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VerifierTest {

	private Scanner scanner;
	private Verifier verifier;

	@Before
	public void setUp() {
		scanner = new Scanner();
		verifier = new Verifier();
	}

	@Test
	public void testExecute() {
		String[] errordatas = {
		// {が存在しない
				" /* a(aaa)*/",
				// (が存在しない
				" /* a{}*/",
				// {が存在しない
				" /* a(aaa)}*/",
				// {が存在しない
				" /*a(aaa){}}*/",
				// 識別子が存在しない
				" /*(aaa){}*/",
				// {がアンマッチ
				" /* a(aaa){*/",
				// (がアンマッチ
				" /*a((aaa){}*/",
				// (がアンマッチ
				" /* a(aaa{}*/",
				// /*がアンマッチ
				" /* a(aaa){}",
				// {の位置がオカシイ
				" /* a(aaa)*/aaa/*{}*/",
				// (の位置がオカシイ
				" /* a(aaa)(){}*/",
				// 識別子の場所がオカシイ
				" /* a(aaa)){}*/", };

		for (String s : errordatas) {
			SqlTokenizeContextImplForUnitTest parameter = new SqlTokenizeContextImplForUnitTest(
					s);
			assertEquals(Success, scanner.execute(parameter));
			assertEquals(s, Fail, verifier.execute(parameter));
			assertTrue(s, 1 == parameter.getMessages().size());
			System.out.println(parameter.getMessages());
		}
		String[] safedatas = { "/* a(aaa)*/*/", "/* a(aaa){*/aaa/*}*/", };
		for (String s : safedatas) {
			SqlTokenizeContextImplForUnitTest parameter = new SqlTokenizeContextImplForUnitTest(
					s);
			assertEquals(Success, scanner.execute(parameter));
			assertEquals(s, Success, verifier.execute(parameter));
			assertTrue(s, 0 == parameter.getMessages().size());
		}

	}

}
