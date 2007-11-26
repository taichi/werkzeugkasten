package com.google.werkzeugkasten.sqlparser.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.werkzeugkasten.meta.ChainContext;
import com.google.werkzeugkasten.meta.impl.AbstractChainContext;
import com.google.werkzeugkasten.sqlparser.SqlParserContext;
import com.google.werkzeugkasten.sqlparser.Status;

public abstract class AbstractSqlParserContext<CTX extends ChainContext<Status>>
		extends AbstractChainContext<Status, CTX> implements SqlParserContext {

	protected char[] fullText;

	protected int cursor;

	protected List<Status> statusList = new ArrayList<Status>();

	public AbstractSqlParserContext(String fulltext) {
		this.fullText = new char[fulltext.length()];
		fulltext.getChars(0, fulltext.length(), this.fullText, 0);
	}

	public char[] getFullText() {
		return this.fullText;
	}

	public void addStatus(Status status) {
		this.statusList.add(status);
	}

	public int getCursor() {
		return this.cursor;
	}

	public List<Status> getStatusCopy() {
		return new ArrayList<Status>(this.statusList);
	}

	public void resetStatus() {
		this.statusList.clear();
	}

	public void setCursor(int index) {
		this.cursor = index;
	}

	public void setStatus(List<Status> status) {
		this.statusList = status;
	}

}
