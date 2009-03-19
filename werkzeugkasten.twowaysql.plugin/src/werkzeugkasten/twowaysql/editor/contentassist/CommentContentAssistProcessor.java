package werkzeugkasten.twowaysql.editor.contentassist;

import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.ELSE;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.ELSEIF;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.IDENT;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.IF;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.LT;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.SYM_BIND;
import static werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer.WHITE_SPACES;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.editor.TwoWaySqlEditor;
import werkzeugkasten.twowaysql.editor.conf.ContextSettings;
import werkzeugkasten.twowaysql.editor.scanner.LexerBasedColoringScanner;
import werkzeugkasten.twowaysql.grammar.NoChannelLexer;
import werkzeugkasten.twowaysql.grammar.TwoWaySqlLexer;
import werkzeugkasten.twowaysql.util.DocumentUtil;

public class CommentContentAssistProcessor implements IContentAssistProcessor,
		IPropertyChangeListener {

	protected String errorMessage;

	protected static final char[] activator = { '.' };

	protected static final String[] KEYWORDS = { "?", "BEGIN", "IF", "ELSEIF",
			"ELSE" };

	protected static final BitSet WHITESPACE_bits = BitSet.of(LT, WHITE_SPACES);
	protected static final BitSet WILLBE_EXPRESSION_bits = BitSet.of(IF,
			ELSEIF, SYM_BIND);
	protected static final BitSet KEYWORDPART_bits = BitSet.of(IDENT, ELSE);
	protected static final BitSet EXPRESSION_PART_bits = LexerBasedColoringScanner.EXPRESSION_bits;

	protected MVELCompletionProposer mvelCollector;

	public CommentContentAssistProcessor(TwoWaySqlEditor editor,
			ContextSettings settings) {
		IPreferenceStore store = Activator.getGlobalPreference();
		store.addPropertyChangeListener(this);
		this.mvelCollector = new MVELCompletionProposer(editor, store, settings);

		// TODO read from preference
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
	}

	@Override
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int offset) {
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		try {
			IDocument doc = viewer.getDocument();
			ITypedRegion partition = DocumentUtil.getPartition(doc,
					Constants.PARTITION_TYPE_TWOWAYSQL, offset);
			List<CommonToken> tokens = parsePartition(doc, partition);
			List<CommonToken> before = getBeforeTokens(offset, partition,
					tokens);
			if (before.size() < 2) {
				// キーワード全部(コメント開始位置もしくは、その内側にカーソルがある)
				CommonToken first = before.get(0);
				for (int i = 0; i < KEYWORDS.length; i++) {
					CompletionProposal cp = new CompletionProposal(KEYWORDS[i],
							partition.getOffset() + first.getStopIndex() + 1,
							0, KEYWORDS[i].length());
					proposals.add(cp);
				}
			} else if (1 < before.size()) {
				CommonToken last = before.get(before.size() - 1);
				// キーワード部分(開始位置にいくつか文字列がある。キーワードリストからフィルタして渡す)
				if (KEYWORDPART_bits.member(last.getType())) {
					String txt = last.getText().toUpperCase();
					for (int i = 0; i < KEYWORDS.length; i++) {
						String s = KEYWORDS[i];
						if (s.startsWith(txt)) {
							CompletionProposal cp = new CompletionProposal(s,
									partition.getOffset()
											+ last.getStartIndex(), txt
											.length(), s.length());
							proposals.add(cp);
						}
					}
				}

				// キーワードが既に完成している。(式言語の入力補完)
				int index = conditionalTokenIndex(tokens);
				if (-1 < index) {
					String maybeExp = "";
					if ((index + 1) < tokens.size()) {
						CommonToken expStart = tokens.get(index + 1);
						CommonToken expEnd = findExpressionEndToken(tokens,
								index + 1);
						if (expEnd != null) {
							maybeExp = doc.get(partition.getOffset()
									+ expStart.getStartIndex(), expEnd
									.getStopIndex()
									- expStart.getStartIndex() + 1);
						}
					}
					// 式言語の入力補完
					proposals.addAll(this.mvelCollector.collect(viewer,
							maybeExp, offset));
				}
			}
			return proposals.toArray(new ICompletionProposal[proposals.size()]);
		} catch (BadPartitioningException e) {
			this.errorMessage = e.getMessage();
			return null;
		} catch (BadLocationException e) {
			this.errorMessage = e.getMessage();
			return null;
		}
	}

	private List<CommonToken> parsePartition(IDocument doc,
			ITypedRegion partition) throws BadLocationException {
		List<CommonToken> result = new ArrayList<CommonToken>();
		String string = doc.get(partition.getOffset(), partition.getLength());

		// System.out.printf("parsePartition %s %n", string);

		TwoWaySqlLexer lex = new NoChannelLexer(new ANTLRStringStream(string));
		CommonToken ct = (CommonToken) lex.nextToken();
		while (ct.getType() != Token.EOF) {
			if (WHITESPACE_bits.member(ct.getType()) == false) {
				result.add(ct);
			}
			ct = (CommonToken) lex.nextToken();
		}
		return result;
	}

	protected List<CommonToken> getBeforeTokens(int offset,
			ITypedRegion partition, List<CommonToken> tokens)
			throws BadLocationException {
		int cursor = offset - partition.getOffset();
		List<CommonToken> before = new ArrayList<CommonToken>();
		for (CommonToken ct : tokens) {
			if (ct.getStopIndex() < cursor) {
				before.add(ct);
			}
		}
		return before;
	}

	protected int conditionalTokenIndex(List<CommonToken> tokens) {
		int result = 0;
		for (CommonToken ct : tokens) {
			if (WILLBE_EXPRESSION_bits.member(ct.getType())) {
				break;
			}
			result++;
		}
		return result;
	}

	protected CommonToken findExpressionEndToken(List<CommonToken> tokens,
			int beginIndex) {
		CommonToken result = null;
		int index = beginIndex;
		int size = tokens.size();
		while (index < size) {
			CommonToken ct = tokens.get(index++);
			if (EXPRESSION_PART_bits.member(ct.getType()) == false) {
				break;
			}
			result = ct;
		}

		return result;
	}

	@Override
	public char[] getCompletionProposalAutoActivationCharacters() {
		return activator;
	}

	@Override
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	@Override
	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		return null;
	}

	@Override
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	@Override
	public String getErrorMessage() {
		return this.errorMessage;
	}

}
