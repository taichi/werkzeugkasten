package com.google.werkzeugkasten.sqlparser.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.werkzeugkasten.meta.ChainContext;
import com.google.werkzeugkasten.meta.impl.AbstractChainContext;
import com.google.werkzeugkasten.sqlparser.SqlContext;
import com.google.werkzeugkasten.sqlparser.Status;
import com.google.werkzeugkasten.sqlparser.TokenKind;
import com.google.werkzeugkasten.sqlparser.TokenLeaf;
import com.google.werkzeugkasten.sqlparser.TokenNode;

public abstract class AbstractSqlContext<CTX extends ChainContext<Status>>
		extends AbstractChainContext<Status, CTX> implements SqlContext {

	protected char[] fullText;
	protected TokenKind[] tokens;

	protected int cursor;

	protected TokenNode root;

	protected List<TokenLeaf> siblings;

	protected List<TokenLeaf> children;

	protected List<Status> statusList = new ArrayList<Status>();

	protected Map<String, Object> ctxparam = new HashMap<String, Object>();

	public AbstractSqlContext(String fulltext) {
		this.fullText = new char[fulltext.length()];
		fulltext.getChars(0, fulltext.length(), this.fullText, 0);
	}

	public AbstractSqlContext(char[] fulltext, TokenKind[] tokens) {
		this.fullText = fulltext;
		this.tokens = tokens;
	}

	public char[] getFullText() {
		return this.fullText;
	}

	public TokenKind[] getTokens() {
		return this.tokens;
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

	public void setRoot(TokenNode token) {
		this.root = token;
	}

	public TokenNode getRoot() {
		return this.root;
	}

	public List<TokenLeaf> getSiblings() {
		return this.siblings;
	}

	public void setSiblings(List<TokenLeaf> siblings) {
		this.siblings = siblings;
	}

	public List<TokenLeaf> getChildren() {
		return this.children;
	}

	public void setChildren(List<TokenLeaf> children) {
		this.children = children;
	}

	public Object get(String key) {
		return this.ctxparam.get(key);
	}

	public void set(String key, Object value) {
		this.ctxparam.put(key, value);
	}

	public Set<String> keys() {
		return this.ctxparam.keySet();
	}
}
