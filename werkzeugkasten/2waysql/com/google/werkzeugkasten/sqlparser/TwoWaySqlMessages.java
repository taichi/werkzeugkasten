package com.google.werkzeugkasten.sqlparser;

import com.google.werkzeugkasten.util.StringLoader;

public class TwoWaySqlMessages {

	public static String SEMANTICCOMMENT;

	public static String IDENTIFIER;

	public static String PARENTHESIS;

	public static String BRACE;

	public static String UNMATCH;

	public static String ILLEGALPOSITION;

	static {
		StringLoader.load(TwoWaySqlMessages.class);
	}
}
