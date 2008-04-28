package twowaysql.grammar;

import java.io.InputStream;

import junit.framework.TestCase;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Parser;
import org.antlr.runtime.TokenSource;

import twowaysql.util.ParserExecutor;

public class TwoWaySQLParserTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();

	}

	public void testComment() throws Exception {
		CharStream input = new ANTLRStringStream("/*BEGIN*/hoge/*END*/");
		TokenSource lexer = new TwoWaySQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Parser parser = new TwoWaySQLParser(tokens);
		Object obj = ParserExecutor.execute("twowaySQL", parser);
		assertNotNull(obj);
		System.out.println(obj);
	}

	public void testTwowaySQL() throws Exception {
		CharStream input = load("twowaySQL.testdata");
		TokenSource lexer = new TwoWaySQLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Parser parser = new TwoWaySQLParser(tokens);
		Object obj = ParserExecutor.execute("twowaySQL", parser);
		assertNotNull(obj);
		System.out.println(obj);
	}

	protected CharStream load(String name) throws Exception {
		String path = "twowaysql/grammar/" + name;
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream input = cl.getResourceAsStream(path);
		return new ANTLRInputStream(input);
	}
}
