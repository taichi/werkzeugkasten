package werkzeugkasten.twowaysql;

public class Constants {

	// The plug-in ID
	public static final String ID_PLUGIN = "werkzeugkasten.twowaysql.plugin";

	public static final String PARTITION_TYPE_TWOWAYSQL = "__twowaysql_";

	/*
	 * content types of twowaysql
	 */
	public static final String CONTENT_TYPE_TEXT = PARTITION_TYPE_TWOWAYSQL
			+ "text_";

	public static final String CONTENT_TYPE_SEMANTIC_COMMENT = PARTITION_TYPE_TWOWAYSQL
			+ "semantic_comment_";

	public static final String CONTENT_TYPE_LINECOMMENT = PARTITION_TYPE_TWOWAYSQL
			+ "linecomment_";

	public static final String CONTENT_TYPE_BLOCKCOMMENT = PARTITION_TYPE_TWOWAYSQL
			+ "blockcomment_";

	public static final String[] LEGAL_CONTENT_TYPES = { CONTENT_TYPE_TEXT,
			CONTENT_TYPE_LINECOMMENT, CONTENT_TYPE_BLOCKCOMMENT };

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
