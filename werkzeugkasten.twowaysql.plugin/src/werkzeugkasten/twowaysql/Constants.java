package werkzeugkasten.twowaysql;

import java.util.ArrayList;
import java.util.List;

import werkzeugkasten.twowaysql.tree.NodeType;

public class Constants {

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
}
