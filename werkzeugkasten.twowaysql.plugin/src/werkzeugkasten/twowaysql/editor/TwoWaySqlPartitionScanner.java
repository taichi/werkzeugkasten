package werkzeugkasten.twowaysql.editor;

import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.C_ED;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.C_LN_ED;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.C_LN_ST;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.C_ST;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer;

public class TwoWaySqlPartitionScanner implements IPartitionTokenScanner {
	// TODO will be tune. cf. FastJavaPartitionScanner

	protected static final IToken PT_TXT = new Token(Constants.CT_TEXT);
	protected static final IToken PT_BLOCKCOMMENT = new Token(
			Constants.CT_BLOCKCOMMENT);
	protected static final IToken PT_LINECOMMENT = new Token(
			Constants.CT_LINECOMMENT);
	protected static final BitSet C_ED_bits = BitSet.of(C_ED);
	protected static final BitSet C_LN_ED_bits = BitSet.of(C_LN_ED);
	protected static final BitSet TXT_bits = BitSet.of(C_ST, C_LN_ST);

	protected CommonTokenStream tokens = null;
	protected int rangeOffset = 0;
	protected int tokenLength = 0;
	protected int tokenOffset = 0;

	public TwoWaySqlPartitionScanner() {
	}

	@Override
	public int getTokenLength() {
		return this.tokenLength;
	}

	@Override
	public int getTokenOffset() {
		return this.rangeOffset + this.tokenOffset;
	}

	@Override
	public IToken nextToken() {
		CommonToken start = (CommonToken) tokens.LT(1);
		int type = start.getType();
		if (type == org.antlr.runtime.Token.EOF) {
			return Token.EOF;
		}

		IToken result = PT_TXT;
		CommonToken stop = null;
		switch (type) {
		case C_ST: {
			stop = consume(C_ED_bits, 1);
			tokens.consume();
			result = PT_BLOCKCOMMENT;
			break;
		}
		case C_LN_ST: {
			stop = consume(C_LN_ED_bits, 1);
			tokens.consume();
			result = PT_LINECOMMENT;
			break;
		}
		default: {
			stop = consume(TXT_bits, -1);
		}
		}
		emit(start, stop);
		// System.out.printf("start[%s %s] stop[%s %s] [%d:%d]%n", start,
		// TwoWaySqlParser.tokenNames[start.getType()], stop,
		// TwoWaySqlParser.tokenNames[stop.getType()], this.tokenOffset,
		// this.tokenLength);
		return result;
	}

	protected CommonToken consume(BitSet stopBit, int index) {
		tokens.consume();
		CommonToken ct = (CommonToken) tokens.LT(1);
		int type = ct.getType();
		while (stopBit.member(type) == false) {
			tokens.consume();
			ct = (CommonToken) tokens.LT(1);
			type = ct.getType();
			if (type == org.antlr.runtime.Token.EOF) {
				return (CommonToken) tokens.LT(-1);
			}
		}
		return (CommonToken) tokens.LT(index);
	}

	protected void emit(CommonToken start, CommonToken stop) {
		this.tokenOffset = start.getStartIndex();
		this.tokenLength = stop.getStopIndex() - start.getStartIndex() + 1;
	}

	@Override
	public void setPartialRange(IDocument document, int offset, int length,
			String contentType, int partitionOffset) {
		try {
			String string = document.get(offset, length);
			// System.out.printf("$PT$ [%s] %n", string);
			rangeOffset = offset;
			tokenLength = 0;
			tokenOffset = 0;
			tokens = new CommonTokenStream(new TwoWaySqlLexer(
					new ANTLRStringStream(string)));
		} catch (BadLocationException e) {
		}
	}

	@Override
	public void setRange(IDocument document, int offset, int length) {
		setPartialRange(document, offset, length, Constants.CT_TEXT, 0);
	}
}
