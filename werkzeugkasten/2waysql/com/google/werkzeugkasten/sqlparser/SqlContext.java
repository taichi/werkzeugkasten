package com.google.werkzeugkasten.sqlparser;

import java.util.List;
import java.util.Set;

import com.google.werkzeugkasten.meta.ChainContext;

public interface SqlContext extends ChainContext<Status> {

	char[] getFullText();

	int getCursor();

	void setCursor(int index);

	void setRoot(TokenNode token);

	TokenNode getRoot();

	List<Status> getStatusCopy();

	void setStatus(List<Status> status);

	void addStatus(Status status);

	void resetStatus();

	Object get(String key);

	void set(String key, Object value);

	Set<String> keys();

}
