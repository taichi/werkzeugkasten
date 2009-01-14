package werkzeugkasten.twowaysql.editor.conf;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.XMLMemento;

import werkzeugkasten.common.util.StringUtil;
import werkzeugkasten.twowaysql.Activator;
import werkzeugkasten.twowaysql.nls.Strings;

public class ContextSettings {

	static final String TAG_ROOT = "context";
	static final String TAG_VAR = "var";
	static final String ATR_VERSION = "version";
	static final String ATR_TYPE = "type";
	static final String ATR_METHOD = "method";
	static final String ATR_NAME = "name";
	static final String ATR_SEQ = "seq";

	protected XMLMemento delegate;

	protected ContextSettings() {
	}

	public static class Var {
		String name;
		String type;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}

	public static ContextSettings read(IPreferenceStore store, IFile readTarget) {
		ContextSettings result = new ContextSettings();
		String memento = store.getString(readTarget.getFullPath()
				.toPortableString());
		try {
			if (StringUtil.isEmpty(memento) == false) {
				result.delegate = XMLMemento.createReadRoot(new StringReader(
						memento));
			}
		} catch (WorkbenchException e) {
			Activator.log(e);
		}
		if (result.delegate == null) {
			result.delegate = XMLMemento.createWriteRoot(TAG_ROOT);
			result.delegate.putString(ATR_VERSION, Strings.BUNDLE_VERSION);
			// XXX バージョンがズレている時の挙動について、あとで考える。
		}
		return result;
	}

	// ScopedPreferenceStore store = new ScopedPreferenceStore(
	// new ProjectScope(readTarget.getProject()), Constants.ID_PLUGIN);

	public static void save(IPersistentPreferenceStore store, IFile saveTarget,
			ContextSettings settings) {
		try {
			StringWriter writer = new StringWriter();
			settings.delegate.save(writer);
			store.setValue(saveTarget.getFullPath().toPortableString(), writer
					.toString());
		} catch (IOException e) {
			Activator.log(e);
		}
	}

	public void setType(String fqn) {
		this.delegate.putString(ATR_TYPE, fqn);
	}

	public void setMethod(String signature) {
		this.delegate.putString(ATR_METHOD, signature);
	}

	public void setVariables(List<Var> vars) {
		for (Var v : vars) {
		}
	}
}
