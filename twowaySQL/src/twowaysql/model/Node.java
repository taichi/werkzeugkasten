package twowaysql.model;

import java.util.List;

import twowaysql.TwowaySQLContext;

public interface Node {

	Node getRoot();

	void setRoot(Node node);

	void add(Node child);

	List<Node> children();

	void execute(TwowaySQLContext context);
}
