package werkzeugkasten.twowaysql.editor.conf;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.Constants;

public class ContextSettings {

	static final String TAG_ROOT = "context";
	static final String TAG_VAR = "var";
	static final String ATR_VERSION = "version";
	static final String ATR_TYPE = "type";
	static final String ATR_METHOD = "method";
	static final String ATR_NAME = "name";
	static final String ATR_SEQ = "seq";

	protected XMLMemento memento;
	protected List<Var> vars;

	protected ContextSettings() {
	}

	public static class Var {
		String name;
		String type;
		String example;

		public String name() {
			return name;
		}

		public void name(String name) {
			this.name = name;
		}

		public String type() {
			return type;
		}

		public void type(String type) {
			this.type = type;
		}

		public String example() {
			return this.example;
		}

		public void example(String example) {
			this.example = example;
		}
	}

	public static ContextSettings read(IPreferenceStore store, IFile readTarget) {
		ContextSettings result = new ContextSettings();
		result.memento = XMLMemento.createWriteRoot(TAG_ROOT);
		result.memento.putString(ATR_VERSION, Constants.BUNDLE_VERSION);
		// XXX バージョンがズレている時の挙動について、あとで考える。
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
					List<Var> vs = new ArrayList<Var>(kids.length);
					for (IMemento m : kids) {
						Var v = new Var();
						v.type = m.getString(ATR_TYPE);
						v.name = m.getString(ATR_NAME);
						vs.add(v);
					}
					result.variables(vs);
				}
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
				Var v = settings.vars.get(i);
				IMemento mnmt = settings.memento.createChild(TAG_VAR);
				mnmt.putString(ATR_NAME, v.name());
				mnmt.putString(ATR_TYPE, v.type());
				mnmt.putInteger(ATR_SEQ, i);
			}
			settings.memento.save(writer);
			store.setValue(saveTarget.getFullPath().toPortableString(), writer
					.toString());
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

	public void variables(List<Var> vars) {
		this.vars = vars;
	}

	public List<Var> variables() {
		return this.vars;
	}
}
