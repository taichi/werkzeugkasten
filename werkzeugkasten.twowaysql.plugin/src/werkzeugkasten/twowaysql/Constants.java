package werkzeugkasten.twowaysql;

public class Constants {

	// The plug-in ID
	public static final String ID_PLUGIN = "werkzeugkasten.twowaysql.plugin";

	/*
	 * content types of twowaysql
	 */
	public static final String CT_TWOWAYSQL = "__twowaysql_";

	public static final String CT_TEXT = CT_TWOWAYSQL + "text_";

	public static final String CT_SEMANTIC_COMMENT = CT_TWOWAYSQL
			+ "semantic_comment_";

	public static final String CT_LINECOMMENT = CT_TWOWAYSQL + "linecomment_";

	public static final String CT_BLOCKCOMMENT = CT_TWOWAYSQL + "blockcomment_";

	public static final String[] LEGAL_CONTENT_TYPES = { CT_TEXT,
			CT_LINECOMMENT, CT_BLOCKCOMMENT };

	public enum COLORING {
		KEYWORD, EXPRESSION, BIND_TYPE, COMMENT, SKIP, MAYBE_SKIP, TXT;

		public String getPrefColorKey() {
			return "PREF_COLOR_" + name();
		}

		public String getPrefStyleKey() {
			return "PREF_STYLE_" + name();
		}
	}
}
