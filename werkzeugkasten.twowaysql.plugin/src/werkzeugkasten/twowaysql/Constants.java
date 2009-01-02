package werkzeugkasten.twowaysql;


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

	public static final String[] LEGAL_CONTENT_TYPES = { CT_TEXT,
			CT_LINECOMMENT, CT_BLOCKCOMMENT };

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
