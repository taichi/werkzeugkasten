package com.google.werkzeugkasten.sqlparser;

import com.google.werkzeugkasten.util.StringLoader;

public class TwoWaySqlMessages {

	public static String TEXT;

	public static String WHITESPACE;

	public static String SEMANTICCOMMENT;

	public static String IDENTIFIER;

	public static String PARENTHESIS;

	public static String BEGINPARENTHESIS;

	public static String ENDPARENTHESIS;

	public static String PARAMETER;

	public static String BRACE;

	public static String BEGINBRACE;

	public static String ENDBRACE;

	public static String UNMATCH;

	public static String ILLEGALPOSITION;

	public static String NOTFOUND;

	static {
		StringLoader.load(TwoWaySqlMessages.class);
	}
}
