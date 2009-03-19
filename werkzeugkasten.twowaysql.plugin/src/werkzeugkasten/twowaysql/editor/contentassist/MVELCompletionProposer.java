package werkzeugkasten.twowaysql.editor.contentassist;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.mvel2.CompileException;
import org.mvel2.ParserConfiguration;
import org.mvel2.ParserContext;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;

import werkzeugkasten.common.jdt.JavaProjectClassLoader;
import werkzeugkasten.common.util.DustCart;
import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.editor.conf.ContextSettings;
import werkzeugkasten.twowaysql.editor.conf.Variable;

public class MVELCompletionProposer implements IPropertyChangeListener {

	protected static final String[] KEYWORDS = { "true", "false", "null",
			"nil", "new", /* "and", "or", */"instanceof", "is", "contains",
			"soundslike", "strsim", "convertable_to", "in", "==", "!=", "<",
			"<=", ">", ">=" };

	protected static final Pattern OPERATORS = Pattern
			.compile(
					"(\\s*(\\+{1,2}|-{1,2}|\\*{1,2}|/|%|==|!=|>=|<=|<{1,3}|>{1,3}|&&|~=|#|&|\\|{1,2}|\\^|:)\\s*|"
							+ "(\\s(and|or|instanceof|is|contains|soundslike|strsim|convertable_to|in)\\s))",
					Pattern.CASE_INSENSITIVE);

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

	public List<ICompletionProposal> collect(ITextViewer viewer,
			String partOfExpression, int offset) {
		System.out.printf("EXP !! <<%s>>%n", partOfExpression);
		List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
		String[] rounded = { "" };

		// TODO パーティションの途中で、入力補完を起動した時の考慮が必要。

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
			collectIncompleteVariables(partOfExpression, offset, result);
		}

		if (result.size() < 1) {
			collectKeywordCompletion(partOfExpression, offset, result);
		}

		// 様々な理由により候補が全くないので、アクセス可能な変数をとりあえず並べる。
		if (result.size() < 1) {
			collectAccessibleVariables("", offset, result);
		}

		return result;
	}

	private void collectKeywordCompletion(String partOfExpression, int offset,
			List<ICompletionProposal> result) {
		String[] ary = partOfExpression.split("\\s");
		if (0 < ary.length) {
			String part = ary[ary.length - 1];
			for (String s : KEYWORDS) {
				if (s.startsWith(part)) {
					CompletionProposal cp = new CompletionProposal(s, offset
							- part.length(), part.length(), s.length());
					result.add(cp);
				}
			}
		}
	}

	private void collectIncompleteVariables(String string, int offset,
			List<ICompletionProposal> result) {
		String prev = getPreviusExpression(string);
		CompiledExpression ce = parseEL(prev, false);

		if (ce != null) {
			ParserContext pc = ce.getParserContext();
			filterIncompleteVariables(string, pc.getVariables());
			filterIncompleteVariables(string, pc.getInputs());
		}

		StringBuilder stb = new StringBuilder();
		Formatter fmt = new Formatter(stb);
		contextToDummyText(ce, fmt);
		stb.append(string);
		requestCodeCompletion(offset, result, stb.toString());
	}

	private void filterIncompleteVariables(String string,
			@SuppressWarnings("unchecked") Map<String, Class> map) {
		for (Iterator<String> i = map.keySet().iterator(); i.hasNext();) {
			String s = i.next();
			if (string.endsWith(s) && Object.class.equals(map.get(s))) {
				i.remove();
			}
		}
	}

	private void collectAccessibleVariables(String string, int offset,
			List<ICompletionProposal> result) {
		String prev = getPreviusExpression(string);
		StringBuilder stb = new StringBuilder();
		Formatter fmt = new Formatter(stb);

		CompiledExpression ce = parseEL(prev, true);
		contextToDummyText(ce, fmt);
		requestCodeCompletion(offset, result, stb.toString());
	}

	private void contextToDummyText(CompiledExpression ce, Formatter fmt) {
		if (ce == null) {
			createDefaultDummyText(fmt);
		} else {
			ParserContext pc = ce.getParserContext();
			contextToDummyText(fmt, pc.getVariables());
			contextToDummyText(fmt, pc.getInputs());
		}
	}

	private void createDefaultDummyText(Formatter fmt) {
		for (Variable v : this.settings.variables()) {
			fmt.format("%s %s;", v.type(), v.name());
		}
	}

	private void contextToDummyText(Formatter fmt,
			@SuppressWarnings("unchecked") Map<String, Class> vars) {
		for (String s : vars.keySet()) {
			fmt.format("%s %s;", vars.get(s).getName(), s);
		}
	}

	private String getPreviusExpression(String string) {
		return splitExpression(string)[0];
	}

	private String[] splitExpression(String string) {
		// これじゃ、ダメじゃね？
		String[] result = new String[2];
		int index = string.lastIndexOf(';');
		if (-1 < index) {
			index += 1;
			result[0] = string.substring(0, index);
			result[1] = string.substring(index);
		} else {
			result[0] = "";
			result[1] = string;
		}
		return result;
	}

	private void collectMemberAccess(String string, int offset,
			List<ICompletionProposal> result) {
		CompiledExpression ce = parseEL(string, true);
		String dummyText = "";
		if (ce != null) {
			Class<?> lastType = ce.getKnownEgressType(); // return type
			if (lastType == null || Object.class.equals(lastType)) {
				return;
			}
			dummyText = buildJavaText(ce, lastType);
		} else {
			StringBuilder stb = new StringBuilder();
			Formatter fmt = new Formatter(stb);
			createDefaultDummyText(fmt);
			dummyText = stb.toString();
		}
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
			// TODO import from context settings.
			// evalContext.setImports(null);

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
		Matcher m = OPERATORS.matcher(exp);
		if (m.find() && exp.length() == m.end()) {
			rounded[0] = m.replaceAll("");
			return true;
		}
		return false;
	}

	private boolean maybeCollectionLiteralPart(String exp, String[] rounded) {
		// TODO Auto-generated method stub
		return false;
	}

	protected CompiledExpression parseEL(String el, boolean strictTyping) {
		CompiledExpression result = null;
		ClassLoader classLoader = null;
		try {
			classLoader = createClassLoader();
			System.out.printf("parseEL %s%n", el);
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
			try {
				ExpressionCompiler compiler = new ExpressionCompiler(el);
				result = compiler.compile(ctx);
			} catch (CompileException e) {
				result = retryELcompile(el, ctx);
			}
			return result;
		} finally {
			this.dustCart.pickUp(classLoader);
		}
	}

	private CompiledExpression retryELcompile(String hasError, ParserContext ctx) {
		CompiledExpression result = null;
		// 最小限の変数アクセスする為にコンパイルを再実行する。
		// 式言語のコンパイルが通らない時に、もっと細かくエラーリカバリするかは、考えた方がよいかも。
		Matcher m = OPERATORS.matcher(hasError);
		String retry = "";
		// 面倒なので、最後のオペレータより後ろだけ取る。
		while (m.find()) {
			retry = hasError.substring(m.end());
		}

		if (StringUtil.isEmpty(retry)) {
			// オペレータが無いので、ステートメントを分割しようとしてみる。
			int index = hasError.lastIndexOf(';');
			if (-1 < index) {
				retry = hasError.substring(index);
			}
		}
		if (StringUtil.isEmpty(retry) == false) {
			ExpressionCompiler compiler = new ExpressionCompiler(retry);
			try {
				result = compiler.compile(ctx);
			} catch (CompileException e2) {
			}
		}
		return result;
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
