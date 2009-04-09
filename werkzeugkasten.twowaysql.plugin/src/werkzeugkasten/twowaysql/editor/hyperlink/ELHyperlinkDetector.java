package werkzeugkasten.twowaysql.editor.hyperlink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.antlr.runtime.BitSet;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetectorExtension2;
import org.eclipse.swt.SWT;
import org.eclipse.ui.texteditor.ITextEditor;

import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.editor.conf.ContextSettings;
import werkzeugkasten.twowaysql.util.DocumentUtil;

public class ELHyperlinkDetector extends AbstractHyperlinkDetector implements
		IHyperlinkDetectorExtension2 {

	public static Set<String> CONTENT_TYPE_COMMENTS = new HashSet<String>(
			Arrays.asList(new String[] { Constants.CONTENT_TYPE_LINECOMMENT,
					Constants.CONTENT_TYPE_BLOCKCOMMENT }));

	protected static final Pattern ELBeforeSpliter = Pattern.compile(
			"(/\\*|--|#)\\s*(if|elseif|(\\?\\w+)?\\?)\\s+",
			Pattern.CASE_INSENSITIVE);

	protected ITextEditor editor;
	protected ContextSettings settings;

	public ELHyperlinkDetector(ITextEditor editor, ContextSettings settings) {
		super();
		this.editor = editor;
		this.settings = settings;
	}

	@Override
	public IHyperlink[] detectHyperlinks(ITextViewer textViewer,
			IRegion region, boolean canShowMultipleHyperlinks) {
		if (textViewer == null || region == null) {
			return null;
		}
		System.out.printf("%s %s %n", region, canShowMultipleHyperlinks);

		List<IHyperlink> result = new ArrayList<IHyperlink>();
		try {
			IDocument doc = textViewer.getDocument();
			int offset = region.getOffset();
			ITypedRegion partition = DocumentUtil.getPartition(doc,
					Constants.PARTITION_TYPE_TWOWAYSQL, offset);
			if (CONTENT_TYPE_COMMENTS.contains(partition.getType())) {
				int begin = DocumentUtil.backto(doc, offset, linkBegin);
				int end = DocumentUtil.skip(doc, offset, linkEnd);
				String string = doc.get(begin, end - begin);
				System.out.println(string);

			}
		} catch (Exception e) {
			Activator.log(e);
		}
		return result.toArray(new IHyperlink[result.size()]);
	}

	protected void detectEL(int offset, String el, List<IHyperlink> links) {

	}

	@Override
	public int getStateMask() {
		return SWT.CTRL;
	}

	protected static final BitSet separators = new BitSet();
	static {
		separators.add('+');
		separators.add('-');
		separators.add('/');
		separators.add('*');
		separators.add('=');
		separators.add('>');
		separators.add('<');
		separators.add('\'');
		separators.add('\"');
		separators.add('^');
		separators.add('%');
		separators.add(':');
		separators.add('&');
		separators.add('|');
	}

	public static final DocumentUtil.Detector linkBegin = new DocumentUtil.Detector() {
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			char ch = d.getChar(index);
			return Character.isWhitespace(ch) == false
					&& separators.member(ch) == false;
		}
	};
	public static final DocumentUtil.Detector linkEnd = new DocumentUtil.Detector() {
		@Override
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			char ch = d.getChar(index);
			return Character.isWhitespace(ch) == false
					&& separators.member(ch) == false && ch != '.';
		}
	};

}
