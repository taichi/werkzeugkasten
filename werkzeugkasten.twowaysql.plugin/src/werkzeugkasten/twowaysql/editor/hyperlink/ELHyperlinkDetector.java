package werkzeugkasten.twowaysql.editor.hyperlink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			ITypedRegion partition = DocumentUtil.getPartition(doc,
					Constants.PARTITION_TYPE_TWOWAYSQL, region.getOffset());
			if (CONTENT_TYPE_COMMENTS.contains(partition.getType())) {
				int offset = region.getOffset();
				int end = DocumentUtil.skip(doc, offset, skipToLinkEnd);

				// back　to index が必要。

				String string = doc.get(partition.getOffset(), end
						- partition.getOffset());
				System.out.println(string);
				Matcher bm = ELBeforeSpliter.matcher(string);
				if (bm.find()) {
					int before = bm.end();
					if (0 < before) {
						string = string.substring(before);
						System.out.println(string);
					}
				}
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

	public static final DocumentUtil.Detector skipToLinkEnd = new DocumentUtil.Detector() {
		@Override
		public boolean detect(IDocument d, int index)
				throws BadLocationException {
			int ch = d.getChar(index);
			if (Character.isWhitespace(ch) || ch == '.') {
				return false;
			}
			if (1 < index) {
				char prev = d.getChar(index - 1);
				if (ch == '/' && prev == '*') {
					return false;
				}
			}
			if (index + 1 < d.getLength()) {
				char next = d.getChar(index + 1);
				if (ch == '*' && next == '/') {
					return false;
				}
			}
			return true;
		}
	};

}
