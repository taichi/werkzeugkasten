package com.google.werkzeugkasten.sqlparser.impl;

import com.google.werkzeugkasten.meta.Chain;
import com.google.werkzeugkasten.sqlparser.SqlTokenizeContext;
import com.google.werkzeugkasten.sqlparser.Status;

public class Scanner implements Chain<Status, SqlTokenizeContext> {

	public Status execute(SqlTokenizeContext parameter) {
		// TODO Auto-generated method stub
		return parameter.execute();
	}
}
