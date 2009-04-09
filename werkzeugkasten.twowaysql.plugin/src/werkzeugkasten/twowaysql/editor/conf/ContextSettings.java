package werkzeugkasten.twowaysql.editor.conf;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;
import org.mvel2.CompileException;
import org.mvel2.ParserConfiguration;
import org.mvel2.ParserContext;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants;

public class ContextSettings {

	public static final Pattern OPERATORS = Pattern
			.compile(
					"(\\s*(\\+{1,2}|-{1,2}|\\*{1,2}|/|%|==|!=|>=|<=|<{1,3}|>{1,3}|&&|~=|#|&|\\|{1,2}|\\^|:)\\s*|"
							+ "(\\s(and|or|instanceof|is|contains|soundslike|strsim|convertable_to|in)\\s))",
					Pattern.CASE_INSENSITIVE);

	static final String TAG_ROOT = "context";
	static final String TAG_VAR = "var";
	static final String ATR_VERSION = "version";
	static final String ATR_TYPE = "type";
	static final String ATR_METHOD = "method";
	static final String ATR_NAME = "name";
	static final String ATR_SEQ = "seq";

	protected XMLMemento memento;
	protected List<Variable> vars;

	protected ContextSettings() {
	}

	public static ContextSettings read(IPreferenceStore store, IFile readTarget) {
		ContextSettings result = new ContextSettings();
		result.memento = XMLMemento.createWriteRoot(TAG_ROOT);
		// XXX バージョンがズレている時の挙動について、あとで考える。
		result.memento.putString(ATR_VERSION, Constants.BUNDLE_VERSION);
		String oldString = store.getString(readTarget.getFullPath()
				.toPortableString());
		try {
			if (StringUtil.isEmpty(oldString) == false) {
				XMLMemento old = XMLMemento.createReadRoot(new StringReader(
						oldString));
				result.memento.putString(ATR_TYPE, old.getString(ATR_TYPE));
				result.memento.putString(ATR_METHOD, old.getString(ATR_METHOD));
				IMemento[] kids = old.getChildren(TAG_VAR);
				if (kids != null) {
					List<Variable> vs = new ArrayList<Variable>(kids.length);
					for (IMemento m : kids) {
						Variable v = new Variable();
						v.type = m.getString(ATR_TYPE);
						v.name = m.getString(ATR_NAME);
						vs.add(v);
					}
					result.variables(vs);
				}
			} else {
				result.variables(new ArrayList<Variable>());
			}
		} catch (WorkbenchException e) {
			Activator.log(e);
		}
		return result;
	}

	public static void save(IPersistentPreferenceStore store, IFile saveTarget,
			ContextSettings settings) {
		try {
			StringWriter writer = new StringWriter();
			for (int i = 0, length = settings.vars.size(); i < length; i++) {
				Variable v = settings.vars.get(i);
				IMemento mnmt = settings.memento.createChild(TAG_VAR);
				mnmt.putString(ATR_NAME, v.name());
				mnmt.putString(ATR_TYPE, v.type());
				mnmt.putInteger(ATR_SEQ, i);
			}
			settings.memento.save(writer);
			store.setValue(saveTarget.getFullPath().toPortableString(), writer
					.toString());
			store.save();
		} catch (IOException e) {
			Activator.log(e);
		}
	}

	public void type(String fqn) {
		this.memento.putString(ATR_TYPE, fqn);
	}

	public String type() {
		return this.memento.getString(ATR_TYPE);
	}

	public void method(String signature) {
		this.memento.putString(ATR_METHOD, signature);
	}

	public String method() {
		return this.memento.getString(ATR_METHOD);
	}

	public void variables(List<Variable> vars) {
		this.vars = vars;
	}

	public List<Variable> variables() {
		return this.vars;
	}

	public CompiledExpression parseEL(ClassLoader classLoader, String el,
			boolean strictTyping) {
		CompiledExpression result = null;
		// System.out.printf("parseEL %s%n", el);
		ParserConfiguration config = new ParserConfiguration();
		config.setClassLoader(classLoader);
		ParserContext ctx = new ParserContext();
		ctx.setStrictTypeEnforcement(strictTyping);
		ctx.setCompiled(true);

		for (Variable v : variables()) {
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
	}

	private CompiledExpression retryELcompile(String hasError, ParserContext ctx) {
		CompiledExpression result = null;
		String retry = concatLastStatement(hasError);
		if (StringUtil.isEmpty(retry) == false) {
			ExpressionCompiler compiler = new ExpressionCompiler(retry);
			try {
				result = compiler.compile(ctx);
			} catch (CompileException e2) {
			}
		}
		return result;
	}

	public String concatLastStatement(String el) {
		String result = "";
		// 最小限の変数アクセスする為にコンパイルを再実行する。
		// 式言語のコンパイルが通らない時に、もっと細かくエラーリカバリするかは、考えた方がよいかも。
		Matcher m = OPERATORS.matcher(el);
		// 面倒なので、最後のオペレータより後ろだけ取る。
		while (m.find()) {
			result = el.substring(m.end());
		}

		if (StringUtil.isEmpty(result)) {
			// オペレータが無いので、ステートメントを分割しようとしてみる。
			int index = el.lastIndexOf(';');
			if (-1 < index) {
				result = el.substring(index);
			} else {
				result = el;
			}
		}
		// System.out.printf("concatLast %n%s%n%s%n", el, result);
		return result;
	}
}
