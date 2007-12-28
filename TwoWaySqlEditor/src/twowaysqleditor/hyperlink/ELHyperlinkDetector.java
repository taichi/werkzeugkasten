package twowaysqleditor.hyperlink;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;

import twowaysqleditor.Activator;
import twowaysqleditor.Constants;
import twowaysqleditor.EditorContext;
import twowaysqleditor.rules.SqlPartitionScanner;
import twowaysqleditor.util.DocumentUtil;
import twowaysqleditor.util.TypeUtil;

public class ELHyperlinkDetector extends AbstractHyperlinkDetector {

	protected EditorContext context;

	public ELHyperlinkDetector(EditorContext context) {
		this.context = context;
	}

	public IHyperlink[] detectHyperlinks(ITextViewer textViewer,
			IRegion region, boolean canShowMultipleHyperlinks) {
		if (region == null || textViewer == null) {
			return null;
		}
		try {
			IDocument document = textViewer.getDocument();

			int offset = region.getOffset();

			String partition = document.getDocumentPartitioner()
					.getContentType(offset);
			if (SqlPartitionScanner.SQL_COMMENT.equals(partition)
					|| SqlPartitionScanner.SQL_IF.equals(partition)) {
				int end = DocumentUtil.skip(document, offset,
						Constants.ignoreCommentEndOrWhitespace) - 1;
				String maybeEL = DocumentUtil.backto(document, end,
						Constants.commentOrWhitespace);
				int begin = end - maybeEL.length() + 1;
				int dot = maybeEL.indexOf('.');
				IJavaElement element = context.getMethod();
				IHyperlink result = null;
				if (0 < dot) {
					if (offset < begin + dot) {
						result = createLink(begin, dot, element);
						// ILocalVariable is not Children of IMethod.
						// if use IMethod#getSource , may be calc target
						// variable offset
					} else {
						result = detectEL(context.getMethod(), maybeEL, offset,
								begin, dot);
					}
				} else {
					result = createLink(begin, end - begin, element);
				}
				if (result != null) {
					return new IHyperlink[] { result };
				}
			}
		} catch (Exception e) {
			Activator.log(e);
		}
		return null;
	}

	public IHyperlink detectEL(IMethod method, String maybeEL, int cursor,
			int begin, int dot) throws CoreException {
		IType type = method.getDeclaringType();
		String[] split = maybeEL.split("\\.");
		String arg = split[0];
		String ptype = null;
		String[] argnames = method.getParameterNames();
		for (int i = 0; i < argnames.length; i++) {
			if (argnames[i].equals(arg)) {
				ptype = method.getParameterTypes()[i];
				break;
			}
		}
		if (ptype != null && 0 < ptype.length()) {
			IJavaProject project = type.getJavaProject();
			IType found = project.findType(TypeUtil.getResolvedTypeName(ptype,
					type));
			int proceed = arg.length() + 1;
			int start = begin + proceed;
			for (int i = 1; i < split.length; i++) {
				String membername = split[i];
				String signature = null;
				if (found != null && found.exists()) {
					int paren = membername.indexOf('(');
					IMember element = null;
					if (paren < 1) {
						IField field = found.getField(membername);
						if (field == null || field.exists() == false) {
							StringBuilder stb = new StringBuilder(membername);
							char ch = Character.toUpperCase(stb.charAt(0));
							stb.setCharAt(0, ch);
							stb.insert(0, "get");
							IMethod mtd = TypeUtil.getAccesserMethod(found, stb
									.toString());
							if (mtd != null && mtd.exists()) {
								signature = mtd.getReturnType();
								element = mtd;
							}
						} else {
							signature = field.getTypeSignature();
							element = field;
						}
					} else {
						String methodname = membername.substring(0, paren);
						IMethod mtd = TypeUtil.getAccesserMethod(found, methodname);
						if (mtd != null && mtd.exists()) {
							signature = mtd.getReturnType();
							element = mtd;
						}
					}
					proceed += membername.length();
					if (cursor < begin + proceed) {
						if (element != null) {
							return createLink(start, membername.length(),
									element);
						}
					}
					start += membername.length() + 1;
					if (signature != null && 0 < signature.length()) {
						found = project.findType(TypeUtil.getResolvedTypeName(
								signature, type));
					} else {
						break;
					}
				}
			}
		}
		IRegion region = new Region(begin + dot + 1, maybeEL.length() - dot - 1);
		return new ELHyperlink(region, method);
	}

	protected IHyperlink createLink(int offset, int length, IJavaElement element) {
		return new ELHyperlink(new Region(offset, length), element);
	}
}
