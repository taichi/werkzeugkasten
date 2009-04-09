package werkzeugkasten.twowaysql.editor.contentassist;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.jdt.core.CompletionContext;
import org.eclipse.jdt.core.IJavaProject;
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
import org.eclipse.ui.texteditor.ITextEditor;
import org.mvel2.ParserContext;
import org.mvel2.compiler.CompiledExpression;

import werkzeugkasten.common.jdt.JavaProjectClassLoader;
import werkzeugkasten.common.runtime.AdaptableUtil;
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

	protected DustCart dustCart = new DustCart();

	protected ITextEditor editor;
	protected ContextSettings settings;

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

	// 考慮事項メモ。以下の項目については、とりあえずサポートしない。
	// (や{が開きっぱなしで閉じてない場合
	// with構文
	// List,配列,Mapリテラルに対するアクセス

	public List<ICompletionProposal> collect(ITextViewer viewer,
			String partOfExpression, int offset) {
		// System.out.printf("EXP !! <<%s>>%n", partOfExpression);
		List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
		String[] rounded = { "" };

		if (endsWith("'", partOfExpression, rounded)
				|| endsWith("\"", partOfExpression, rounded)) {
			// 文字リテラルを記述しようとしている為、補完候補が存在しない
			// do nothing.
		} else if (endsWith(".", partOfExpression, rounded)) {
			// .で終わっている場合
			// hoge.
			// TODO hoge.fu > hoge.fuga等、プロパティアクセスが不完全な時に足りない部分を補完しる。
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
		} else {
			// その他。主に、不完全な変数名が入力状態である時。
			// hog
			collectIncompleteVariables(partOfExpression, offset, result);
		}
		if (result.size() < 1) {
			collectKeywordCompletion(partOfExpression, offset, result);
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
		int index = string.lastIndexOf(';');
		if (-1 < index) {
			return string.substring(0, index);
		}
		return string;
	}

	private void collectMemberAccess(String string, int offset,
			List<ICompletionProposal> result) {
		// .アクセスなので、直前の変数だけを考慮対象にすれば、大体良い筈。
		// (hoge && fuga). みたいなのを今のところ考慮しない。
		CompiledExpression ce = parseEL(this.settings
				.concatLastStatement(string), true);
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
			IJavaProject project = AdaptableUtil.to(this.editor,
					IJavaProject.class);
			if (project != null) {
				CompletionProposalCollector collector = new ELCompletionProposalCollector(
						project, offset - dummyText.length());
				collector.acceptContext(new CompletionContext());
				IEvaluationContext evalContext = project.newEvaluationContext();
				// TODO import from context settings.
				// evalContext.setImports(null);

				evalContext.codeComplete(dummyText, dummyText.length(),
						collector);

				for (IJavaCompletionProposal jcp : collector
						.getJavaCompletionProposals()) {
					result.add(jcp);
				}
				// Comparator使う為には、internal APIに触らないといけなくて、
				// それはイマイチ筋が悪いので、とりあえず反転しる。
				Collections.reverse(result);
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
		// BooleanやLong等、単にLowerしただけでは、妥当な型名になってしまうケースに対する対応。
		return "_" + lastName;
	}

	private boolean endsWith(String string, String exp, String[] rounded) {
		if (exp.endsWith(string)) {
			rounded[0] = exp.substring(0, exp.length() - string.length());
			return true;
		}
		return false;
	}

	private boolean endsWithOperator(String exp, String[] rounded) {
		Matcher m = ContextSettings.OPERATORS.matcher(exp);
		if (m.find() && exp.length() == m.end()) {
			rounded[0] = m.replaceAll("");
			return true;
		}
		return false;
	}

	protected CompiledExpression parseEL(String el, boolean strictTyping) {
		ClassLoader classLoader = createClassLoader();
		try {
			return this.settings.parseEL(classLoader, el, strictTyping);
		} finally {
			this.dustCart.pickUp(classLoader);
		}
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

}
