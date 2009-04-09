package werkzeugkasten.twowaysql.editor.hyperlink;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.BitSet;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetectorExtension2;
import org.eclipse.swt.SWT;
import org.eclipse.ui.texteditor.ITextEditor;
import org.mvel2.compiler.CompiledExpression;

import werkzeugkasten.common.jdt.JavaProjectClassLoader;
import werkzeugkasten.common.jdt.TypeHierarchyWalker;
import werkzeugkasten.common.runtime.AdaptableUtil;
import werkzeugkasten.common.util.DustCart;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants;
import werkzeugkasten.twowaysql.editor.conf.ContextSettings;
import werkzeugkasten.twowaysql.editor.conf.Variable;
import werkzeugkasten.twowaysql.util.DocumentUtil;

public class ELHyperlinkDetector extends AbstractHyperlinkDetector implements
		IHyperlinkDetectorExtension2 {

	public static Set<String> CONTENT_TYPE_COMMENTS = new HashSet<String>(
			Arrays.asList(new String[] { Constants.CONTENT_TYPE_LINECOMMENT,
					Constants.CONTENT_TYPE_BLOCKCOMMENT }));

	protected DustCart dustCart = new DustCart();
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
		// System.out.printf("%s %s %n", region, canShowMultipleHyperlinks);

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
				// System.out.println(string);
				String[] ary = string.split("\\.");
				if (0 < ary.length) {
					IType element = findType(ary[0]);
					Region range = new Region(begin, end - begin);
					if (element != null) {
						if (1 < ary.length) {
							detectEL(range, string, element, result);
						} else {
							result.add(new ELHyperlink(range, element));
						}
					}
				}
			}
		} catch (Exception e) {
			Activator.log(e);
		}
		return result.toArray(new IHyperlink[result.size()]);
	}

	protected void detectEL(Region region, String el, IType root,
			List<IHyperlink> links) throws Exception {
		int dot = el.lastIndexOf('.');
		if (0 < dot) {
			ClassLoader classLoader = createClassLoader();
			try {
				String string = el.substring(0, dot);
				final String cond = el.substring(dot + 1);
				CompiledExpression expression = this.settings.parseEL(
						classLoader, string, true);
				Class<?> clazz = expression.getKnownEgressType();
				IJavaProject jp = AdaptableUtil.to(this.editor,
						IJavaProject.class);
				if (jp != null) {
					IType type = jp.findType(clazz.getName());
					IMember m = searchMember(type, cond);
					if (m != null && m.exists()) {
						links.add(new ELHyperlink(region, m));
					} else {
						final IMember[] result = new IMember[1];
						new TypeHierarchyWalker(type,
								new TypeHierarchyWalker.TypeHierarchyHandler() {
									@Override
									public void begin() {
									}

									@Override
									public void done() {
									}

									@Override
									public boolean handle(IType type)
											throws JavaModelException {
										result[0] = searchMember(type, cond);
										return result[0] == null;
									}
								}).run(null);
						if (result[0] != null) {
							links.add(new ELHyperlink(region, m));
						}
					}
				}
			} finally {
				this.dustCart.pickUp(classLoader);
			}
		}

	}

	protected IMember searchMember(IType type, String cond)
			throws JavaModelException {
		int paren = cond.indexOf('(');
		if (0 < paren) {
			String s = cond.substring(0, paren);
			for (IMethod m : type.getMethods()) {
				// オーバーロードとか、勘弁してほしい感じ。
				if (m.getElementName().equalsIgnoreCase(s)) {
					return m;
				}
			}
		} else {
			String accesser = "get" + cond;
			for (IMethod m : type.getMethods()) {
				if (m.getElementName().equalsIgnoreCase(accesser)
						&& m.getNumberOfParameters() < 1) {
					return m;
				}
			}
			for (IField f : type.getFields()) {
				if (f.getElementName().equalsIgnoreCase(cond)) {
					return f;
				}
			}
		}
		return null;
	}

	protected IType findType(String name) {
		try {
			IJavaProject jp = AdaptableUtil.to(this.editor, IJavaProject.class);
			if (jp != null) {
				for (Variable v : this.settings.variables()) {
					if (v.name().equals(name)) {
						IType t = jp.findType(v.type());
						if (t != null && t.exists()) {
							return t;
						}
					}
				}
			}
		} catch (JavaModelException e) {
			Activator.log(e);
		}
		return null;
	}

	protected CompiledExpression parseEL(String el, boolean strictTyping) {
		ClassLoader classLoader = createClassLoader();
		return this.settings.parseEL(classLoader, el, strictTyping);
	}

	protected ClassLoader createClassLoader() {
		ClassLoader result = null;
		IJavaProject jp = AdaptableUtil.to(this.editor, IJavaProject.class);
		if (jp != null) {
			result = new JavaProjectClassLoader(jp) {
				@Override
				public void log(Throwable t) {
					Activator.log(t);
				}
			};
		} else {
			result = new URLClassLoader(new URL[0]);
		}
		return result;
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
		// XXX 要考察。
		// separators.add('\'');
		// separators.add('\"');
		separators.add('^');
		separators.add('%');
		separators.add(':');
		separators.add('&');
		separators.add('|');
		separators.add(';');
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
