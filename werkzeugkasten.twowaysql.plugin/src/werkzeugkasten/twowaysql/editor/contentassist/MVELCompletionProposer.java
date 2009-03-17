package werkzeugkasten.twowaysql.editor.contentassist;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.CompletionContext;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.eval.IEvaluationContext;
import org.eclipse.jdt.ui.text.java.CompletionProposalCollector;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposal;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.mvel2.ParserConfiguration;
import org.mvel2.ParserContext;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;

import werkzeugkasten.common.jdt.JavaProjectClassLoader;
import werkzeugkasten.common.util.DustCart;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.editor.conf.ContextSettings;
import werkzeugkasten.twowaysql.editor.conf.Variable;

public class MVELCompletionProposer implements IPropertyChangeListener {

	protected static final List<String> LITERALS = new ArrayList<String>();
	protected static final List<String> OPERATORS = new ArrayList<String>();

	static {
		for (String s : AbstractParser.LITERALS.keySet()) {
			LITERALS.add(s);
		}
		Collections.sort(LITERALS);
		try {
			AbstractParser.setLanguageLevel(AbstractParser.LEVEL_1_BASIC_LANG);
			for (String s : AbstractParser.OPERATORS.keySet()) {
				OPERATORS.add(s);
			}
			Collections.sort(OPERATORS);
		} finally {
			AbstractParser
					.setLanguageLevel(AbstractParser.LEVEL_5_CONTROL_FLOW);
		}
	}

	protected ITextEditor editor;
	protected ContextSettings settings;
	protected DustCart dustCart = new DustCart();

	public MVELCompletionProposer(ITextEditor editor, IPreferenceStore store,
			ContextSettings settings) {
		store.addPropertyChangeListener(this);
		this.settings = settings;
		this.editor = editor;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO read from configuration...
	}

	// 考慮事項メモ
	// (や{が開きっぱなしで閉じてない場合
	// with構文
	// キーワード対応的なアレ
	// true/false null nil new with assert isdef !

	public List<ICompletionProposal> collect(ITextViewer viewer,
			String partOfExpression, int offset) {
		System.out.printf("EXP !! <<%s>>%n", partOfExpression);
		List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
		String[] rounded = { "" };
		if (endsWith("'", partOfExpression, rounded)
				|| endsWith("\"", partOfExpression, rounded)) {
			// 文字リテラルを記述しようとしている為、補完候補が存在しない
			// do nothing.
		} else if (endsWith(".", partOfExpression, rounded)) {
			// .で終わっている場合
			// hoge.
			collectMemberAccess(rounded[0], offset, result);
		} else if (StringUtil.isEmpty(partOfExpression)
				|| endsWith(",", partOfExpression, rounded)
				|| endsWithOperator(partOfExpression, rounded)) {
			// 式が存在しない場合、もしくはコンマかコロンかオペレータで終わっている場合
			// hoge.fuga(aaa,
			// [aaa,
			// {aaa,
			// hoge['fuga':
			// hoge <
			collectAccessibleVariables(rounded[0], offset, result);
		} else if (maybeCollectionLiteralPart(partOfExpression, rounded)) {
			// mapリテラル、listリテラル、配列リテラルっぽい場合
			// hoge[
			// TODO not implemented. what needly ?
		} else {
			// その他。主に、不完全な変数名が入力状態である時。
			// hog
			// collectAccessibleVariables(rounded[0], offset, result);
		}
		return result;
	}

	private void collectAccessibleVariables(String string, int offset,
			List<ICompletionProposal> result) {
		String prev = getPreviusExpression(string);
		CompiledExpression ce = parseEL(prev, true);
		StringBuilder stb = new StringBuilder();
		Formatter fmt = new Formatter(stb);
		contextToDummyText(ce, fmt);
		requestCodeCompletion(offset, result, stb.toString());
	}

	private void contextToDummyText(CompiledExpression ce, Formatter fmt) {
		ParserContext pc = ce.getParserContext();
		contextToDummyText(fmt, pc.getVariables());
		contextToDummyText(fmt, pc.getInputs());
	}

	@SuppressWarnings("unchecked")
	private void contextToDummyText(Formatter fmt, Map<String, Class> vars) {
		for (String s : vars.keySet()) {
			fmt.format("%s %s;", vars.get(s).getName(), s);
		}
	}

	private String getPreviusExpression(String string) {
		int index = string.lastIndexOf(';');
		if (-1 < index) {
			return string.substring(0, index);
		}
		return string;
	}

	private void collectMemberAccess(String string, int offset,
			List<ICompletionProposal> result) {
		CompiledExpression ce = parseEL(string, true);
		Class<?> lastType = ce.getKnownEgressType(); // return type
		if (lastType == null) {
			return;
		}
		String dummyText = buildJavaText(ce, lastType);
		requestCodeCompletion(offset, result, dummyText);
	}

	private void requestCodeCompletion(int offset,
			List<ICompletionProposal> result, String dummyText) {
		try {
			IJavaProject project = getJavaProject();
			CompletionProposalCollector collector = new ELCompletionProposalCollector(
					project, offset - dummyText.length());
			collector.acceptContext(new CompletionContext());
			IEvaluationContext evalContext = project.newEvaluationContext();
			// evalContext.setImports(null); // need imports really

			evalContext.codeComplete(dummyText, dummyText.length(), collector);

			for (IJavaCompletionProposal jcp : collector
					.getJavaCompletionProposals()) {
				result.add(jcp);
			}
		} catch (JavaModelException e) {
			Activator.log(e);
		}
	}

	protected String buildJavaText(CompiledExpression expression,
			Class<?> lastType) {
		StringBuilder stb = new StringBuilder();
		Formatter fmt = new Formatter(stb);
		String lastName = buildJavaText(expression, lastType, fmt);
		fmt.format("%s %s;", lastType.getName(), lastName);
		fmt.format("%s.", lastName);
		return stb.toString();
	}

	private String buildJavaText(CompiledExpression expression,
			Class<?> lastType, Formatter fmt) {
		String lastName = "";
		ParserContext pc = expression.getParserContext();
		@SuppressWarnings("unchecked")
		Map<String, Class> vars = pc.getVariables();
		for (String v : vars.keySet()) {
			Class<?> clazz = vars.get(v);
			if (lastType.equals(clazz)) {
				lastName = v;
			} else {
				fmt.format("%s %s;", clazz.getName(), v);
			}
		}
		contextToDummyText(fmt, pc.getInputs());
		if (StringUtil.isEmpty(lastName)) {
			lastName = lastType.getSimpleName().toLowerCase();
		}
		return lastName;
	}

	private boolean endsWith(String string, String exp, String[] rounded) {
		if (exp.endsWith(string)) {
			rounded[0] = exp.substring(0, exp.length() - string.length());
			return true;
		}
		return false;
	}

	private boolean endsWithOperator(String exp, String[] rounded) {
		for (String s : OPERATORS) {
			if (endsWith(s, exp, rounded)) {
				return true;
			}
		}
		return false;
	}

	private boolean maybeCollectionLiteralPart(String exp, String[] rounded) {
		// TODO Auto-generated method stub
		return false;
	}

	protected CompiledExpression parseEL(String el, boolean strictTyping) {
		ClassLoader classLoader = createClassLoader();
		try {
			ParserConfiguration config = new ParserConfiguration();
			config.setClassLoader(createClassLoader());
			ParserContext ctx = new ParserContext();
			ctx.setStrictTypeEnforcement(strictTyping);
			ctx.setCompiled(true);

			for (Variable v : this.settings.variables()) {
				try {
					Class<?> clazz = classLoader.loadClass(v.type());
					ctx.addInput(v.name(), clazz);
				} catch (ClassNotFoundException e) {
					Activator.log(e);
				}
			}
			ExpressionCompiler compiler = new ExpressionCompiler(el);
			return compiler.compile(ctx);
		} finally {
			this.dustCart.pickUp(classLoader);
		}
	}

	protected ClassLoader createClassLoader() {
		ClassLoader result = null;
		IJavaProject jp = getJavaProject();
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

	protected IJavaProject getJavaProject() {
		IEditorInput input = this.editor.getEditorInput();
		if (input instanceof IFileEditorInput) {
			IFileEditorInput fei = (IFileEditorInput) input;
			IFile file = fei.getFile();
			return JavaCore.create(file.getProject());
		}
		return null;
	}
}
