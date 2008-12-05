package werkzeugkasten.twowaysql.grammar;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.junit.Test;

import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser.twowaySQL_return;

public class TwoWaySqlParserTest {

	protected String data(String path) throws Exception {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		InputStream in = null;
		try {
			in = cl.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			StringBuilder stb = new StringBuilder();
			while (br.ready()) {
				stb.append(br.readLine());
				stb.append("\r\n");
			}
			return stb.toString();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	protected twowaySQL_return runParser(String sql) throws Exception {
		ANTLRStringStream in = new ANTLRStringStream(sql);
		TwoWaySqlLexer lex = new TwoWaySqlLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		TwoWaySqlParser parser = new TwoWaySqlParser(tokens);
		return parser.twowaySQL();
	}

	@Test
	public void testfull() throws Exception {
		String sql = data("werkzeugkasten/twowaysql/grammar/test.txt");
		twowaySQL_return ret = runParser(sql);
		System.out.println(ret.tree);
	}

	@Test
	public void testIfError() throws Exception {
		String sql = "SELECT /*IF true*/* FROM HOGE;";
		twowaySQL_return ret = runParser(sql);
		assertNotNull(ret);
	}
}
