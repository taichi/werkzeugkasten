package twowaysqleditor.hyperlink;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
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
				IRegion newone = null;
				IJavaElement element = context.getMethod();
				if (0 < dot) {
					if (offset < begin + dot) {
						newone = new Region(begin, dot);
						// ILocalVariable is not Children of IMethod.
						// if use IMethod#getSource , i can calc target variable
						// offset
					} else {
						newone = new Region(begin + dot + 1, maybeEL.length()
								- dot - 1);
						element = findParameterType(context.getMethod(),
								maybeEL, dot);
					}
				} else {
					newone = new Region(begin, end - begin);
				}
				return new IHyperlink[] { new ELHyperlink(newone, element) };
			}
		} catch (Exception e) {
			Activator.log(e);
		}
		return null;
	}

	public IJavaElement findParameterType(IMethod method, String maybeEL,
			int dot) throws CoreException {
		IType type = method.getDeclaringType();
		String arg = maybeEL.substring(0, dot);
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
			if (found != null && found.exists()) {
				String membername = maybeEL.substring(dot + 1);
				if (found.isBinary()) {
					return found;
				}
				for (IField f : found.getFields()) {
					if (f.getElementName().equals(membername)) {
						return f;
					}
				}
				String getter = "get" + membername;
				for (IMethod m : found.getMethods()) {
					if (m.getNumberOfParameters() < 1
							&& m.getElementName().equalsIgnoreCase(getter)) {
						return m;
					}
				}
			}
		}
		return method;
	}
}
