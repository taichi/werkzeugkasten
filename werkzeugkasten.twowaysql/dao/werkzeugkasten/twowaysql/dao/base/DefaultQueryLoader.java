package werkzeugkasten.twowaysql.dao.base;

import java.io.InputStream;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import werkzeugkasten.common.util.Streams;
import werkzeugkasten.twowaysql.Markers;
import werkzeugkasten.twowaysql.dao.QueryLoader;
import werkzeugkasten.twowaysql.dao.QueryWrapper;
import werkzeugkasten.twowaysql.error.ProblemCoordinator;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlParser;
import werkzeugkasten.twowaysql.nls.Messages;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeAcceptor;
import werkzeugkasten.twowaysql.tree.visitor.QueryTreeVisitor;

public class DefaultQueryLoader implements QueryLoader<String> {

	static final Logger LOG = LoggerFactory.getLogger(DefaultQueryLoader.class);

	@Override
	public QueryWrapper load(String context) {
		final String source = loadSource(context);
		return new QueryWrapper() {
			@Override
			public String getSource() {
				return source;
			}

			public <C, V extends QueryTreeVisitor<C>> void accept(V visitor,
					C context) {
				ProblemCoordinator pc = new ProblemCoordinator();
				ANTLRStringStream in = new ANTLRStringStream(source);
				TwoWaySqlLexer lex = new TwoWaySqlLexer(in);
				CommonTokenStream tokens = new CommonTokenStream(lex);
				TwoWaySqlParser parser = new TwoWaySqlParser(tokens);
				parser.setProblemCoordinator(pc);
				try {
					if (LOG.isDebugEnabled()) {
						LOG.debug(Markers.INTERFACE, Messages.PARSE_TWOWAYSQL);
					}
					TwoWaySqlParser.twowaySQL_return ret = parser.twowaySQL();
					if (LOG.isDebugEnabled()) {
						LOG
								.debug(Markers.DETAIL,
										Messages.VISIT_TWOWAYSQL_TREE);
					}
					QueryTreeAcceptor.accept(ret.query, visitor, context);
				} catch (RecognitionException e) {
					// do nothing.
				}
			};
		};
	}

	protected String loadSource(final String context) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(Markers.INTERFACE, Messages.LOAD_RESOURCE, context);
		}
		final String[] result = new String[1];
		new Streams.using<InputStream, Exception>(Exception.class) {
			@Override
			public InputStream open() throws Exception {
				ClassLoader cl = Thread.currentThread().getContextClassLoader();
				return cl.getResourceAsStream(context);
			}

			@Override
			public void handle(InputStream stream) throws Exception {
				result[0] = Streams.readText(stream);
			}

			@Override
			public void happen(Exception exception) {
				throw new IllegalStateException(exception);
			}
		};
		return result[0];
	}
}
