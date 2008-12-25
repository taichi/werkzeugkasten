package werkzeugkasten.twowaysql;

import java.util.ArrayList;
import java.util.List;

import werkzeugkasten.twowaysql.tree.NodeType;

public class Constants {

	// The plug-in ID
	public static final String ID_PLUGIN = "werkzeugkasten.twowaysql.plugin";

	/*
	 * content types of twowaysql
	 */
	public static final String CT_TWOWAYSQL = "__twowaysql_";

	public static final String CT_TEXT = "__twowaysql_text_";

	public static final String CT_LINECOMMENT = "__twowaysql_linecomment_";

	public static final String CT_BLOCKCOMMENT = "__twowaysql_blockcomment_";

	public static final String[] LEGAL_CONTENT_TYPES;

	static {
		List<String> list = new ArrayList<String>();
		for (NodeType nt : NodeType.values()) {
			list.add("__" + nt.name());
		}
		LEGAL_CONTENT_TYPES = list.toArray(new String[list.size()]);
	}

	public enum COLORING {
		KEYWORD, EXPRESSION, COMMENT, SKIP, MAYBE_SKIP, TXT;

		public String getPrefColorKey() {
			return "PREF_COLOR_" + name();
		}

		public String getPrefStyleKey() {
			return "PREF_STYLE_" + name();
		}
	}
}
