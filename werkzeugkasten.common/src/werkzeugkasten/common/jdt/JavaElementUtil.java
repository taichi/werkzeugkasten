package werkzeugkasten.common.jdt;

import java.util.regex.Pattern;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import werkzeugkasten.common.resource.ProjectUtil;
import werkzeugkasten.common.runtime.AdaptableUtil;

public class JavaElementUtil {

	public static ITextEditor selectAndReveal(IMember member)
			throws CoreException {
		IEditorPart part = JavaUI.openInEditor(member);
		ITextEditor editor = AdaptableUtil.to(part, ITextEditor.class);
		ISourceRange range = member.getNameRange();
		editor.selectAndReveal(range.getOffset(), 0);
		return editor;
	}

	public static ICompilationUnit to(IJavaElement element) {
		ICompilationUnit result = null;
		if (element == null) {
		} else if (element.getElementType() == IJavaElement.COMPILATION_UNIT) {
			result = (ICompilationUnit) element;
		} else if (element instanceof IMember) {
			IMember m = (IMember) element;
			result = m.getCompilationUnit();
		}
		return result;
	}

	public static boolean find(IProject project, IPath path, Pattern rsptn,
			FindingHandler handler) throws JavaModelException, CoreException {
		IWorkspaceRoot workspaceRoot = project.getWorkspace().getRoot();
		IJavaProject javap = JavaCore.create(project);
		IPackageFragmentRoot[] roots = javap.getPackageFragmentRoots();
		for (int i = 0; i < roots.length; i++) {
			IPackageFragmentRoot root = roots[i];
			if (root.getKind() == IPackageFragmentRoot.K_SOURCE) {
				IPath p = root.getPath().append(path);
				if (workspaceRoot.exists(p)) {
					if (find(workspaceRoot.getFolder(p), rsptn, handler)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean find(IContainer dir, Pattern rsptn,
			FindingHandler handler) throws CoreException {
		IResource[] files = dir.members(IResource.FILE);
		for (int j = 0; j < files.length; j++) {
			IResource file = files[j];
			if (file.getType() == IResource.FILE
					&& rsptn.matcher(file.getName()).matches()) {
				IFile f = (IFile) file;
				handler.handle(f);
				return true;
			}
		}
		return false;
	}

	public interface FindingHandler {
		public FindingHandler NULL = new NullFindingHandler();

		public void handle(IFile file);
	}

	public static class NullFindingHandler implements FindingHandler {
		public void handle(IFile file) {
		}
	}

	public static ASTNode parse(IResource r) {
		if (r.getType() != IResource.FILE) {
			return null;
		}
		IFile file = (IFile) r;
		ICompilationUnit unit = JavaCore.createCompilationUnitFrom(file);
		return parse(unit);
	}

	public static ASTNode parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setCompilerOptions(unit.getJavaProject().getOptions(true));
		parser.setSource(unit);
		return parser.createAST(null);
	}

	public static IJavaProject getJavaProject(String projectName) {
		return JavaCore.create(ProjectUtil.getProject(projectName));
	}

	public static IJavaProject getJavaProject(IResource resource) {
		return JavaCore.create(resource.getProject());
	}

	public static IJavaProject[] getJavaProjects() throws CoreException {
		return getJavaModel().getJavaProjects();
	}

	public static IJavaModel getJavaModel() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		return JavaCore.create(workspace.getRoot());
	}

	public static IJavaProject getJavaProject(IPath path) throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(
				path.segment(0));
		return JavaCore.create(project);
	}
}
