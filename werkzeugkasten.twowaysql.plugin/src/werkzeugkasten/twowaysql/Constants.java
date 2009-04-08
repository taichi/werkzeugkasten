package werkzeugkasten.twowaysql;

import org.eclipse.jface.text.IDocument;

import werkzeugkasten.common.util.StringUtil;

public class Constants {

	public static final String BUNDLE_VERSION = StringUtil.toString(Activator
			.getBundleHeaders().get("Bundle-Version"), "0.0.0");

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

	public static final String[] LEGAL_CONTENT_TYPES = {
			IDocument.DEFAULT_CONTENT_TYPE, CONTENT_TYPE_TEXT,
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

	/*
	 * annotation types.
	 */
	public static final String ANNOTATION_TYPE = Constants.ID_PLUGIN
			+ ".annotation";

	public static final String ANNOTATION_TYPE_ERROR = ANNOTATION_TYPE
			+ ".error";
	public static final String ANNOTATION_TYPE_WARN = ANNOTATION_TYPE + ".warn";
	public static final String ANNOTATION_TYPE_INFO = ANNOTATION_TYPE + ".info";

}
